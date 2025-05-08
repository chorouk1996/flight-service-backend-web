package com.service.backend.web.exceptions;

public class FunctionalException extends RuntimeException{

    private final FunctionalExceptionDto functionalExceptionDto;

    public FunctionalExceptionDto getFunctionalExceptionDto() {
        return functionalExceptionDto;
    }


    public FunctionalException(FunctionalExceptionDto functionalExceptionDto) {
        this.functionalExceptionDto = functionalExceptionDto;
    }

    @Override
    public String toString() {
        return functionalExceptionDto.toString();
    }
}
