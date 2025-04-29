package com.lam.sb_backend.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PokemonRecordDTO(
        UUID recordId,
        String pokemonId,
        LocalDateTime captureTime,
        UUID userId,
        boolean isRelease
) {
}
