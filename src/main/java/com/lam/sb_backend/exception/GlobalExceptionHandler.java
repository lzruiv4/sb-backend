package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * User
     * */
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<SBResponseException> handleUserNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseException(ErrorCode.NOT_FOUND, e.getMessage(), e.getCause().toString()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PasswordInvalidException.class})
    public ResponseEntity<SBResponseException> handlePasswordInvalidException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseException(ErrorCode.PASSWORD_INVALID, e.getMessage(), e.getCause().toString()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PasswordUpdateTheSameAsOldException.class})
    public ResponseEntity<SBResponseException> handlePasswordUpdateTheSameAsOldException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause().toString()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Recharge
     * */
    @ExceptionHandler({CoinRechargeInvalidException.class})
    public ResponseEntity<SBResponseException> handleCoinRechargeInvalidException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseException(ErrorCode.RECHARGE_INPUT_INVALID, e.getMessage(), e.getCause().toString()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Pokemon record
     * */
    @ExceptionHandler({PokemonRecordNotFoundException.class})
    public ResponseEntity<SBResponseException> handlePokemonRecordNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseException(ErrorCode.POKEMON_RECORD_NOT_FOUND, e.getMessage(), e.getCause().toString()),
                HttpStatus.BAD_REQUEST);
    }
}
