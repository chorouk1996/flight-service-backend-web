package com.service.backend.web.aspect;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public FunctionalExceptionDto httpMessageNotReadableException(HttpServletRequest request, Exception exception){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        request.getRequestURL();
        ex.setMessage(exception.getMessage());
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.CONFLICT);
        ex.setTimestamp(LocalDateTime.now());
        return ex;
    }

    @ExceptionHandler(exception = UsernameNotFoundException.class)
    @ResponseBody
    public FunctionalExceptionDto usernameNotFoundException(HttpServletRequest request){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        request.getRequestURL();
        ex.setMessage("User Not Found");
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.NOT_FOUND);
        ex.setTimestamp(LocalDateTime.now());
        ex.setError("User_Not_Found");
        return ex;
    }

    @ExceptionHandler(exception = BadCredentialsException.class)
    @ResponseBody
    public FunctionalExceptionDto badCredentialsException(HttpServletRequest request){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setMessage("bad credentials");
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.UNAUTHORIZED);
        ex.setTimestamp(LocalDateTime.now());
        ex.setError("Bad_Credentials");
        return ex;
    }


    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    @ResponseBody
    public FunctionalExceptionDto methodArgumentNotValidException(HttpServletRequest request){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        ex.setMessage("Invalid request data");
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        ex.setTimestamp(LocalDateTime.now());
        ex.setError("INVALID_REQUEST_DATA");
        return ex;
    }

    @ExceptionHandler(exception = FunctionalException.class)
    @ResponseBody
    public FunctionalExceptionDto expiredJwtException(HttpServletRequest request,FunctionalException exception){

        exception.getFunctionalExceptionDto().setPath(request.getMethod()+" " + request.getRequestURI());
        return exception.getFunctionalExceptionDto();
    }

    @ExceptionHandler(exception = Exception.class)
    @ResponseBody
    public FunctionalExceptionDto globalException(HttpServletRequest request, Exception exception){
        FunctionalExceptionDto ex = new FunctionalExceptionDto();
        request.getRequestURL();
        ex.setMessage(exception.getMessage());
        ex.setPath(request.getRequestURI());
        ex.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        ex.setTimestamp(LocalDateTime.now());
        return ex;
    }

}
