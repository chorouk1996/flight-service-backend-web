package com.service.backend.web.exceptions;

public class FunctionalException extends RuntimeException{

    FunctionalExceptionDto functionalExceptionDto;

    public FunctionalExceptionDto getFunctionalExceptionDto() {
        return functionalExceptionDto;
    }

    public void setFunctionalExceptionDto(FunctionalExceptionDto functionalExceptionDto) {
        this.functionalExceptionDto = functionalExceptionDto;
    }

    public FunctionalException(FunctionalExceptionDto functionalExceptionDto) {
        this.functionalExceptionDto = functionalExceptionDto;
    }

    @Override
    public String toString() {
        return functionalExceptionDto.toString();
    }
}
