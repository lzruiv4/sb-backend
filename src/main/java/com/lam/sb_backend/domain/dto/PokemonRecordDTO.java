package com.lam.sb_backend.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record PokemonRecordDTO(
        UUID id,
        @NotNull(message = "pokemonId can not be null")
        String pokemonId,
        LocalDateTime captureTime,
        @NotNull(message = "userId can not be null")
        UUID userId,
        boolean isRelease
) {
}
