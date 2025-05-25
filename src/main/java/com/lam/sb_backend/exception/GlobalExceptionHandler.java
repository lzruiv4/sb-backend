package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<SBResponseException> handleUserNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseException(ErrorCode.NOT_FOUND, "User not found", e.getCause().toString()),
                HttpStatus.NOT_FOUND);
    }
}
