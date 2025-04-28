package com.lam.sb_backend.service;

import com.lam.sb_backend.domain.dto.UserDTO;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    Optional<UserDTO> getUserById(UUID userId);

    UserDTO addNewUser(UserDTO userDTO);
}
