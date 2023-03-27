package com.oppari.springbootbackend.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message, Long id) {
        super(message + id);
    }
}