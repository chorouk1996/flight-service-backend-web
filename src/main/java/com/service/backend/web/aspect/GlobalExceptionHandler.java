package com.service.backend.web.aspect;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = {HttpMessageNotReadableException.class, NoResourceFoundException.class})
    @ResponseBody
    public FunctionalExceptionDto httpMessageNotReadableException(HttpServletResponse response, HttpServletRequest request, Exception exception){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        request.getRequestURL();
        ex.setMessage(exception.getMessage());
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.CONFLICT);
        response.setStatus(ex.getStatus().value());
        ex.setTimestamp(LocalDateTime.now());
        return ex;
    }

    @ExceptionHandler(exception = UsernameNotFoundException.class)
    @ResponseBody
    public FunctionalExceptionDto usernameNotFoundException(HttpServletResponse response,HttpServletRequest request){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        request.getRequestURL();
        ex.setMessage("User Not Found");
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.NOT_FOUND);
        response.setStatus(ex.getStatus().value());
        ex.setTimestamp(LocalDateTime.now());
        ex.setError("User_Not_Found");
        return ex;
    }

    @ExceptionHandler(exception = BadCredentialsException.class)
    @ResponseBody
    public FunctionalExceptionDto badCredentialsException(HttpServletResponse response, HttpServletRequest request){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setMessage("bad credentials");
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.UNAUTHORIZED);
        response.setStatus(ex.getStatus().value());
        ex.setTimestamp(LocalDateTime.now());
        ex.setError("Bad_Credentials");
        return ex;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public FunctionalExceptionDto handleValidationException(HttpServletResponse response, HttpServletRequest request, MethodArgumentNotValidException exception) {
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setTimestamp(LocalDateTime.now());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        response.setStatus(ex.getStatus().value());
        ex.setError("INVALID_REQUEST_DATA");
        ex.setPath(request.getRequestURI());

        // Extract the first validation error message
        String defaultMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Invalid request data");
        ex.setMessage(defaultMessage);
        return ex;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public FunctionalExceptionDto httpRequestMethodNotSupportedException(HttpServletResponse response, HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setTimestamp(LocalDateTime.now());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        ex.setError("METHOD_NOT_SUPPORTED");
        ex.setPath(request.getRequestURI());
        response.setStatus(ex.getStatus().value());
        // Extract the first validation error message

        ex.setMessage("Method " + exception.getMethod() + " is not supported");
        return ex;
    }

    @ExceptionHandler(exception = FunctionalException.class)
    @ResponseBody
    public FunctionalExceptionDto expiredJwtException(HttpServletResponse response, HttpServletRequest request,FunctionalException exception){

        exception.getFunctionalExceptionDto().setPath(request.getMethod()+" " + request.getRequestURI());
        return exception.getFunctionalExceptionDto();
    }

    @ExceptionHandler(exception = Exception.class)
    @ResponseBody
    public FunctionalExceptionDto globalException(HttpServletResponse response, HttpServletRequest request, Exception exception){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        request.getRequestURL();
        ex.setMessage(exception.getMessage());
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setStatus(ex.getStatus().value());
        ex.setTimestamp(LocalDateTime.now());
        return ex;
    }

}
