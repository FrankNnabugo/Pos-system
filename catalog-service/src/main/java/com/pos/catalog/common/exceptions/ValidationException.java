package com.pos.catalog.common.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }
}
