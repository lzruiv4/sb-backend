package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthUsernameNotFoundException extends RuntimeException {
    public AuthUsernameNotFoundException(String username, Throwable errorMethod) {
        super("Username " + username + " is not found, you can register it.", errorMethod);
    }
}
