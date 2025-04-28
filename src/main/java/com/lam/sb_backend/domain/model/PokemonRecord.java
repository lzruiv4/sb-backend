package com.lam.sb_backend.domain.model;

import com.lam.sb_backend.domain.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

public class PokemonRecord {
    private UUID recordId;
    private String pokemonId;
    private LocalDate captureTime;
    private UserEntity user;
    private boolean isRelease;
}
