package com.lam.sb_backend.domain.dto;

import jakarta.validation.constraints.NotNull;

public record UserLoginDTO(
        @NotNull(message = "Username can not be null")
        String username,
        @NotNull(message = "Password can not be null")
        String password
) {
}
