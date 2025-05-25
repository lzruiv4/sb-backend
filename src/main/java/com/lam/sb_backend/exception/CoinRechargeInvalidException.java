package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CoinRechargeInvalidException extends RuntimeException {
    public CoinRechargeInvalidException(Throwable errorMethod) {
        super("Coin can not small as 1, please check input", errorMethod);
    }
}
