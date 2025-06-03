package com.lam.sb_backend.serviceImp;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.dto.UserRegisterDTO;
import com.lam.sb_backend.domain.dto.UserRegisterResponseDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.domain.model.User;
import com.lam.sb_backend.exception.AuthUsernameAlreadyRegisterException;
import com.lam.sb_backend.exception.AuthUsernameNotFoundException;
import com.lam.sb_backend.exception.PasswordInvalidException;
import com.lam.sb_backend.exception.PasswordUpdateTheSameAsOldException;
import com.lam.sb_backend.exception.UserNotFoundException;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.repository.IUserRepository;
import com.lam.sb_backend.security.RoleDetails;
import com.lam.sb_backend.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImp implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(RoleDetails::new)
                .orElseThrow(() -> new AuthUsernameNotFoundException(username, new Throwable("loadUserByUsername")));
    }

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
                .sorted(Comparator.comparing(UserEntity::getCreatedAt).reversed())
                .map(IUserMapper.INSTANCE::entityToDto)
                .toList();
    }

    @Override
    public UserDTO updateUser(UUID userId, User user) {
        String password = userRepository.findById(userId)
                .map(UserEntity::getPassword)
                .orElseThrow(() -> new UserNotFoundException(userId, new Throwable("updateUser")));
        user.setUserId(userId);
        user.setPassword(password);
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
        if(oldPassword.equals(newPassword)) {
            throw new PasswordUpdateTheSameAsOldException(new Throwable("updatePassword"));
        } else {
            if (passwordEncoder.matches(oldPassword, userEntity.getPassword())) {
                userEntity.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(userEntity);
            } else {
                throw new PasswordInvalidException(new Throwable("updatePassword"));
            }
        }
    }

    @Override
    public UserRegisterResponseDTO register(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByUsername(userRegisterDTO.username()).isPresent()) {
            throw new AuthUsernameAlreadyRegisterException(userRegisterDTO.username(), new Throwable("register"));
        }
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(userRegisterDTO.username());
        newUserEntity.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
        newUserEntity.setCreatedAt(LocalDateTime.now());
        newUserEntity.setFirstname(userRegisterDTO.firstname());
        newUserEntity.setLastname(userRegisterDTO.lastname());
        newUserEntity.setPokemonCoin(10);
        newUserEntity.getRoles().addAll(userRegisterDTO.roles());
        UserEntity result = userRepository.save(newUserEntity);
        return IUserMapper.INSTANCE.entityToUserRegisterResponseDTO(result);
    }
}
