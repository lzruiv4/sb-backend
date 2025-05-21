package com.lam.sb_backend.exception;

import lombok.Getter;

@Getter
public class SBException extends RuntimeException{

    private final ErrorCode errorCode;

    public SBException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
