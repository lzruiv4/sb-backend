package com.lam.sb_backend.serviceImp;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.domain.model.User;
import com.lam.sb_backend.exception.PasswordUpdateTheSameAsOldException;
import com.lam.sb_backend.exception.UserNotFoundException;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.repository.IUserRepository;
import com.lam.sb_backend.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImp implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(IUserMapper.INSTANCE::entityToDto)
                .orElseThrow(
                        () -> new UserNotFoundException(userId, new Throwable("getUserById"))
                );
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
    public UserDTO updateUser(UUID userId, User user) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId, new Throwable("updateUser")));
        user.setUserId(userId);
        UserEntity updatedUser = userRepository.save(IUserMapper.INSTANCE.modelToEntity(user));
        return IUserMapper.INSTANCE.entityToDto(updatedUser);
    }

    @Override
    public void updatePassword(UUID userId, String oldPassword, String newPassword) {
        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(userId, new Throwable("updatePassword"))
                );
        // password should be valid in frontend，backend check only the old password is right or wrong
        if(passwordEncoder.matches(oldPassword, userEntity.getPassword())){
            throw new PasswordUpdateTheSameAsOldException("updatePassword");
        } else {
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
        }
    }
}
