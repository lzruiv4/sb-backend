package com.lam.sb_backend.domain.dto;

import java.util.UUID;

public record TokenDTO(
        UUID userId,
        String token
) {
}
