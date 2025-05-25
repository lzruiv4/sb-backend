package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PasswordUpdateTheSameAsOldException extends RuntimeException {

    private String errorMethod;

//    public PasswordUpdateTheSameAsOldException() {
//        super("Password update the same as old");
//    }

    public PasswordUpdateTheSameAsOldException(String errorMethod) {
        super("Password update the same as old");
        this.errorMethod = "Method: " + errorMethod;
    }
}
