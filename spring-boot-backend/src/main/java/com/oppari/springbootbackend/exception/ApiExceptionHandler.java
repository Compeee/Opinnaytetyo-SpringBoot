package com.oppari.springbootbackend.exception;


import com.oppari.springbootbackend.authentication.RegisterRequest;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {
    @ResponseBody
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String PostNotFoundHandler(PostNotFoundException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CommentNotFoundHandler(CommentNotFoundException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String UserNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmailAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String EmailAlreadyInUseHandler(EmailAlreadyInUseException ex){
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> ValidationExceptionHandler(ValidationException ex){
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<RegisterRequest> violation : ex.getViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        }
        return ResponseEntity.badRequest().body(errors);
    }
    @ResponseBody
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String InvalidPasswordHandler(InvalidPasswordException ex){
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String InvalidCredentialsHandler(InvalidCredentialsException ex){
        return ex.getMessage();
    }

}
