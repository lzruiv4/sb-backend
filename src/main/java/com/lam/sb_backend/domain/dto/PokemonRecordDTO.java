package com.lam.sb_backend.domain.dto;

import com.lam.sb_backend.domain.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

public record PokemonRecordDTO(
        UUID recordId,
        String pokemonId,
        LocalDate captureTime,
        UserEntity user,
        boolean isRelease
) {
}
