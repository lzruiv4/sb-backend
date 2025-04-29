package com.lam.sb_backend.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDTO(
        UUID userId,
        String username,
        LocalDateTime createdAt,
        String firstname,
        String lastname,
        int pokemonCoin
) {
}
