package com.lam.sb_backend.domain.dto;

import java.util.UUID;

public record UserRegisterResponseDTO(
        UUID userId,
        String username,
        String firstname,
        String lastname
) {
}
