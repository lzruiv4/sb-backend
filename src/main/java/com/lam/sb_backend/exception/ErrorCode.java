package com.lam.sb_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SUCCESS("200"),
    UNAUTHORIZED("403"),
    NOT_FOUND("404"),
    INTERNAL_SERVER_ERROR("500"),

    /**
     * Some error code
     * */
    PASSWORD_EMPTY("PASSWORD_100"),
    PASSWORD_SAME("PASSWORD_101"),
    PASSWORD_WRONG("PASSWORD_102"),
    PASSWORD_INVALID("PASSWORD_103"),
    PASSWORD_CAN_NOT_BE_SAVE("PASSWORD_104"),

    RECHARGE_INPUT_INVALID("RE_RE_401"),
    RECHARGE_RECORD_NOT_FOUND("RE_RE_404"),

    POKEMON_RECORD_NOT_FOUND("PO_RE_404");

    private final String httpCode;
}
