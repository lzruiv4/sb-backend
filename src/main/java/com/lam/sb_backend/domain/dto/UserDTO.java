package com.lam.sb_backend.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UserDTO(
        UUID userId,
        String username,
        LocalDate createdAt,
        String firstname,
        String lastname,
        int pokemonCoin
) {
}
