package com.lam.sb_backend.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PokemonRecordCreateDTO(
        @NotNull(message = "Pokemon ID can not be null")
        String pokemonId,
        @NotNull(message = "User ID can not be null")
        UUID userId
) {
}
