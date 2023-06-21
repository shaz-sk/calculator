package com.mywave.calculator.exception;
public class InvalidOperationException extends RuntimeException {
    private final String message;
    public InvalidOperationException (String message){
        this.message = message;
    }
}
