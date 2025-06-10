package com.service.backend.web.exceptions;

import org.springframework.http.HttpStatus;

public class FunctionalException extends RuntimeException{

    private final FunctionalExceptionDto functionalExceptionDto;

    public FunctionalExceptionDto getFunctionalExceptionDto() {
        return functionalExceptionDto;
    }


    public FunctionalException(FunctionalExceptionDto functionalExceptionDto) {
        this.functionalExceptionDto = functionalExceptionDto;
    }

    public FunctionalException(String message, HttpStatus status) {
        this.functionalExceptionDto = new FunctionalExceptionDto(message,status);
    }
    @Override
    public String toString() {
        return functionalExceptionDto.toString();
    }
}
