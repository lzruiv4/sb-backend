package com.lam.sb_backend.domain.dto;

import com.lam.sb_backend.domain.enums.Role;

import java.util.Set;

public record UserRegisterDTO(
        String username,
        String firstname,
        String lastname,
        String password,
        Set<Role> roles
) {
}
