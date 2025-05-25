package com.lam.sb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonRecordNotFoundException extends RuntimeException {
    public PokemonRecordNotFoundException(UUID pokemonRecordId, Throwable errorMethod) {
        super("PokemonRecord ID " + pokemonRecordId + " is not found", errorMethod);
    }
}
