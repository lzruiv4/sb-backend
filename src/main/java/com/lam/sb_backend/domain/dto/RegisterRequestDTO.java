package com.lam.sb_backend.domain.dto;

public record RegisterRequestDTO(
        String username,
        String firstname,
        String lastname,
        String password
) {
}
