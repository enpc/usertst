package com.example.rest.services;

public class UserParametersException extends RuntimeException {
    public UserParametersException(Throwable cause) {
        super(cause);
    }

    public UserParametersException(String message){
        super(message);
    }

    public UserParametersException(String message, Throwable cause){
        super(message, cause);
    }
}
