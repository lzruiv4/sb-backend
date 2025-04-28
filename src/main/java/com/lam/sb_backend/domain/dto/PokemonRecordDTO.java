package com.lam.sb_backend.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PokemonRecordDTO(
        UUID recordId,
        String pokemonId,
        LocalDate captureTime,
        UUID userId,
        boolean isRelease
) {
}
