package com.ferc.businessrulesdemo.exceptions;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException {
    private String action;
    public ValidationException(String action, String message) {
        super(message);
        this.action = action;
    }
}
