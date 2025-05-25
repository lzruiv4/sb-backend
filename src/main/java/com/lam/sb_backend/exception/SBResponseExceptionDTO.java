package com.lam.sb_backend.exception;

public record SBResponseExceptionDTO(
        ErrorCode errorCode,
        String message,
        String cause
) {}
