package com.lam.sb_backend.serviceImp;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.domain.model.User;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.repository.IUserRepository;
import com.lam.sb_backend.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImp implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public UserDTO getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(IUserMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(IUserMapper.INSTANCE::entityToDto)
                .toList();
    }

    @Override
    public UserDTO addNewUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        UserEntity savedUserEntity = userRepository.save(IUserMapper.INSTANCE.modelToEntity(user));
        return IUserMapper.INSTANCE.entityToDto(savedUserEntity);
    }

    @Override
    public UserDTO updateUser(User user) {
        userRepository.findById(user.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserEntity updatedUser = userRepository.save(IUserMapper.INSTANCE.modelToEntity(user));
        return IUserMapper.INSTANCE.entityToDto(updatedUser);
    }
}
