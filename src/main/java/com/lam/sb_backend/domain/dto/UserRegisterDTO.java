package com.lam.sb_backend.domain.dto;

import com.lam.sb_backend.domain.enums.Role;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserRegisterDTO(
        @NotNull(message = "Username can not be null")
        String username,
        @NotNull(message = "Firstname can not be null")
        String firstname,
        @NotNull(message = "Lastname can not be null")
        String lastname,
        @NotNull(message = "Password can not be null")
        String password,
        Set<Role> roles
) {
}
