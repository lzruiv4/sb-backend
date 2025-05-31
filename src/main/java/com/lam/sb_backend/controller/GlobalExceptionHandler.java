package com.lam.sb_backend.controller;

import com.lam.sb_backend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Bad request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<SBResponseExceptionDTO>> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<SBResponseExceptionDTO> errorResponses = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new SBResponseExceptionDTO(
                        ErrorCode.INPUT_BAD_REQUEST,
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();
        return ResponseEntity.badRequest().body(errorResponses);
    }

    // Authentication
    @ExceptionHandler({AuthUsernameNotFoundException.class})
    public ResponseEntity<SBResponseExceptionDTO> handleUsernameNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseExceptionDTO(ErrorCode.USERNAME_NOT_FOUND, e.getMessage(), e.getCause().toString()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AuthUsernameAlreadyRegisterException.class})
    public ResponseEntity<SBResponseExceptionDTO> handleAuthUsernameAlreadyRegisterException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseExceptionDTO(ErrorCode.USERNAME_ALREADY_EXISTS, e.getMessage(), e.getCause().toString()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<SBResponseExceptionDTO> handleBadCredentials(BadCredentialsException e) {
        return new ResponseEntity<>(
                new SBResponseExceptionDTO(
                        ErrorCode.USERNAME_OR_PASSWORD_INVALID,
                        "Please check you username or password",
                        null),
                HttpStatus.UNAUTHORIZED);
    }

    // User
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<SBResponseExceptionDTO> handleUserNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseExceptionDTO(ErrorCode.USER_NOT_FOUND, e.getMessage(), e.getCause().toString()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PasswordInvalidException.class})
    public ResponseEntity<SBResponseExceptionDTO> handlePasswordInvalidException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseExceptionDTO(ErrorCode.PASSWORD_INVALID, e.getMessage(), e.getCause().toString()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PasswordUpdateTheSameAsOldException.class})
    public ResponseEntity<SBResponseExceptionDTO> handlePasswordUpdateTheSameAsOldException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseExceptionDTO(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause().toString()),
                HttpStatus.BAD_REQUEST);
    }

    // Recharge
    @ExceptionHandler({CoinRechargeInvalidException.class})
    public ResponseEntity<SBResponseExceptionDTO> handleCoinRechargeInvalidException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseExceptionDTO(ErrorCode.RECHARGE_INPUT_INVALID, e.getMessage(), e.getCause().toString()),
                HttpStatus.BAD_REQUEST);
    }

    // Pokemon record
    @ExceptionHandler({PokemonRecordNotFoundException.class})
    public ResponseEntity<SBResponseExceptionDTO> handlePokemonRecordNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(
                new SBResponseExceptionDTO(ErrorCode.POKEMON_RECORD_NOT_FOUND, e.getMessage(), e.getCause().toString()),
                HttpStatus.BAD_REQUEST);
    }
}
