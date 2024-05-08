package com.javarush.springbootdouble.service.exception;

public class RegistrationRuntimeException extends RuntimeException {
    public RegistrationRuntimeException() {
    }

    public RegistrationRuntimeException(String message) {
        super(message);
    }
}
