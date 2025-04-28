package com.lam.sb_backend.domain.dto;

import java.time.LocalDate;

public record UserDTO(
        String userId,
        String username,
        LocalDate createdAt,
        String firstname,
        String lastname,
        int pokemonCoin
) {
}
