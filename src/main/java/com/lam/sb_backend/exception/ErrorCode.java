package com.lam.sb_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // User
    USER_NOT_FOUND("USER_404"),

    PASSWORD_EMPTY("USER_PASSWORD_100"),
    PASSWORD_SAME("USER_PASSWORD_101"),
    PASSWORD_WRONG("USER_PASSWORD_102"),
    PASSWORD_INVALID("USER_PASSWORD_103"),
    PASSWORD_CAN_NOT_BE_SAVE("USER_PASSWORD_104"),

    // Recharge
    RECHARGE_INPUT_INVALID("RE_RE_401"),
    RECHARGE_RECORD_NOT_FOUND("RE_RE_404"),

    // Pokemon record
    POKEMON_RECORD_NOT_FOUND("PO_RE_404"),

    INTERNAL_SERVER_ERROR("500");

    private final String httpCode;
}
