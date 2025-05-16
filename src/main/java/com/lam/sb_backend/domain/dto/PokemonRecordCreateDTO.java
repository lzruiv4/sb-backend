package com.lam.sb_backend.domain.dto;

import java.util.UUID;

public record PokemonRecordCreateDTO(
        String pokemonId,
        UUID userId
) {
}
