package com.example.rest.services.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
