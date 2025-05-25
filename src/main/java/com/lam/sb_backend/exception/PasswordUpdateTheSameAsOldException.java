package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordUpdateTheSameAsOldException extends RuntimeException {

    public PasswordUpdateTheSameAsOldException(Throwable errorMethod) {
        super("Password update the same as old", errorMethod);
    }
}
