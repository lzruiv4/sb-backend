package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthUsernameAlreadyRegisterException extends RuntimeException {
    public AuthUsernameAlreadyRegisterException(String username, Throwable errorMethod) {
        super("Username " + username + " is already found, please try another one.", errorMethod);
    }
}
