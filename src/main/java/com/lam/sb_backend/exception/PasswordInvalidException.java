package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException(Throwable errorMethod) {
        super("Your password is incorrect", errorMethod);
    }
}
