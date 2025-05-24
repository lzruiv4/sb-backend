package com.lam.sb_backend.domain.dto;

import com.lam.sb_backend.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String username,
        LocalDateTime createdAt,
        String firstname,
        String lastname,
        int pokemonCoin,
        Set<Role> roles
) {
}
