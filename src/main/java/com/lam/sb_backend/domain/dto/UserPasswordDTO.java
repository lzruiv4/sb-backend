package com.lam.sb_backend.domain.dto;

import jakarta.validation.constraints.NotNull;

public record UserPasswordDTO(
        @NotNull(message = "Password can not be null")
        String oldPassword,
        @NotNull(message = "Password can not be null")
        String newPassword
) {
}
