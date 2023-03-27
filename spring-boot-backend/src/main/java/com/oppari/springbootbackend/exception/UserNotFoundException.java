package com.oppari.springbootbackend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message, Long id) {
        super(message + id);
    }
}
