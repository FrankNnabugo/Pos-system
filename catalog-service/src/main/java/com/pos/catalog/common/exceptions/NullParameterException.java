package com.pos.catalog.common.exceptions;

public class NullParameterException extends RuntimeException{
    public NullParameterException(String message) {
        super(message);
    }
}
