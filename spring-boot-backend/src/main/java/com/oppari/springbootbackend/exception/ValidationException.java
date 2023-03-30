package com.oppari.springbootbackend.exception;

import com.oppari.springbootbackend.authentication.RegisterRequest;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class ValidationException extends RuntimeException {
    private final Set<ConstraintViolation<RegisterRequest>> violations;

    public ValidationException(Set<ConstraintViolation<RegisterRequest>> violations) {
        this.violations = violations;
    }

    public Set<ConstraintViolation<RegisterRequest>> getViolations() {
        return violations;
    }
}

