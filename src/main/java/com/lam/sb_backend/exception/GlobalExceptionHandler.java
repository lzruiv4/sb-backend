package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(SBException.class)
//    public ResponseEntity<SBException> handleUserNotFoundException(SBException e) {
//        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(new SBException(ErrorCode.USER_NOT_FOUND, e.getMessage()));
//    }
}
