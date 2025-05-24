package com.lam.sb_backend.domain.dto;

public record UserPasswordDTO(
        String oldPassword,
        String newPassword
) {
}
