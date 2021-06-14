package com.example.kata.service.exception;

public class ValidationException extends BaseException {

    public ValidationException() {
        super();
    }

    public ValidationException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}

