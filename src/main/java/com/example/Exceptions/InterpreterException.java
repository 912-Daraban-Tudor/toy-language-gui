package com.example.Exceptions;

public class InterpreterException extends Exception{
    public InterpreterException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
