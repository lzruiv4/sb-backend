package com.lam.sb_backend.service;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    UserDTO getUserById(UUID userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UUID userId, User user);

    void updatePassword(UUID userId, String oldPassword, String newPassword);

    //TODO: Maybe later add delete the user
}
