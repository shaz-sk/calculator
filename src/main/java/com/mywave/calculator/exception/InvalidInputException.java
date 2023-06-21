package com.mywave.calculator.exception;

import org.springframework.validation.Errors;

public class InvalidInputException extends RuntimeException {
    private final transient Errors errors;
    public InvalidInputException(final Errors errors){
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
