package com.service.backend.web.aspect;

import com.service.backend.web.constantes.ErrorMessages;
import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.security.SignatureException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({HttpMessageNotReadableException.class, NoResourceFoundException.class})
    @ResponseBody
    public FunctionalExceptionDto httpMessageNotReadableException(HttpServletResponse response, HttpServletRequest request, Exception exception) {
        LOGGER.warn("Malformed request at {}: {}", request.getRequestURI(), exception.getMessage());
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setMessage(exception.getMessage());
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.CONFLICT);
        response.setStatus(ex.getStatus().value());
        ex.setTimestamp(LocalDateTime.now());
        return ex;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public FunctionalExceptionDto usernameNotFoundException(HttpServletResponse response, HttpServletRequest request) {
        LOGGER.warn("Username not found at {}", request.getRequestURI());
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setMessage(ErrorMessages.USER_NOT_FOUND);
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.NOT_FOUND);
        response.setStatus(ex.getStatus().value());
        ex.setTimestamp(LocalDateTime.now());
        ex.setError(ErrorMessages.USER_NOT_FOUND);
        return ex;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public FunctionalExceptionDto badCredentialsException(HttpServletResponse response, HttpServletRequest request) {
        LOGGER.warn("Bad credentials attempt at {}", request.getRequestURI());
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setMessage("Incorrect email or password");
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.UNAUTHORIZED);
        response.setStatus(ex.getStatus().value());
        ex.setTimestamp(LocalDateTime.now());
        ex.setError("BAD_CREDENTIALS");
        return ex;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public FunctionalExceptionDto handleValidationException(HttpServletResponse response, HttpServletRequest request, MethodArgumentNotValidException exception) {
        String defaultMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Invalid request data");

        LOGGER.warn("Validation error at {}: {}", request.getRequestURI(), defaultMessage);

        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setTimestamp(LocalDateTime.now());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        response.setStatus(ex.getStatus().value());
        ex.setError("INVALID_REQUEST_DATA");
        ex.setPath(request.getRequestURI());
        ex.setMessage(defaultMessage);
        return ex;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public FunctionalExceptionDto httpRequestMethodNotSupportedException(HttpServletResponse response, HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        LOGGER.warn("Unsupported method {} on {}", exception.getMethod(), request.getRequestURI());
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setTimestamp(LocalDateTime.now());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        ex.setError("METHOD_NOT_SUPPORTED");
        ex.setPath(request.getRequestURI());
        response.setStatus(ex.getStatus().value());
        ex.setMessage("Method " + exception.getMethod() + " is not supported");
        return ex;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public FunctionalExceptionDto constraintViolationException(HttpServletResponse response, HttpServletRequest request, ConstraintViolationException exception) {
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setTimestamp(LocalDateTime.now());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        ex.setError("INVALID_REQUEST_DATA");
        ex.setPath(request.getRequestURI());
        response.setStatus(ex.getStatus().value());
        ex.setMessage(exception.getMessage());
        return ex;
    }

    @ExceptionHandler(FunctionalException.class)
    @ResponseBody
    public FunctionalExceptionDto handleFunctional(HttpServletResponse response, HttpServletRequest request, FunctionalException exception) {
        LOGGER.warn("Functional exception at {} {}: {}", request.getMethod(), request.getRequestURI(), exception.getMessage());
        exception.getFunctionalExceptionDto().setPath(request.getMethod() + " " + request.getRequestURI());
        return exception.getFunctionalExceptionDto();
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public FunctionalExceptionDto signatureException(HttpServletResponse response, HttpServletRequest request) {
        LOGGER.warn("Invalid token signature at {}", request.getRequestURI());
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setMessage("Invalid token");
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.UNAUTHORIZED);
        response.setStatus(ex.getStatus().value());
        ex.setTimestamp(LocalDateTime.now());
        ex.setError("INVALID_TOKEN");
        return ex;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public FunctionalExceptionDto handleAll(Exception ex, HttpServletRequest request) {
        LOGGER.error("Unhandled exception", ex); // côté backend : tout est loggué

        return FunctionalExceptionDto.builder()
                .message("Une erreur interne est survenue. Merci de réessayer plus tard.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .path(request.getMethod() + " " + request.getRequestURI())
                .error("UNEXPECTED_ERROR")
                .build();
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public FunctionalExceptionDto accessDenied(HttpServletResponse response, HttpServletRequest request) {
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setMessage("Access is denied");
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.FORBIDDEN);
        ex.setError("ACCESS_DENIED");
        ex.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return ex;
    }
}