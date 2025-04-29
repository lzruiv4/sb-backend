package com.lam.sb_backend.service;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    UserDTO getUserById(UUID userId);

    List<UserDTO> getAllUsers();

    UserDTO addNewUser(User user);

    UserDTO updateUser(User user);

    //TODO: Maybe later add delect the user
}
