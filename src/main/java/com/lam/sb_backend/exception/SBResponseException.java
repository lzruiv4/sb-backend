package com.lam.sb_backend.exception;

public record SBResponseException(
        ErrorCode errorCode,
        String message,
        String cause
) {}
