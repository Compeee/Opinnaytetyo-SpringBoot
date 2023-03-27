package com.oppari.springbootbackend.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String message, Long id) {
        super(message + id);
    }
}
