package com.assignment.calculator.exception;


public class InvalidDataException extends Exception{
    private static final long serialVersionUID = 1L;
    public InvalidDataException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
