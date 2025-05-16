package com.lam.sb_backend.domain.dto;

public record UserRegisterDTO(
        String username,
        String firstname,
        String lastname,
        String password
) {
}
