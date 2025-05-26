package com.lam.sb_backend.security;

import com.lam.sb_backend.domain.dto.UserRegisterDTO;
import com.lam.sb_backend.domain.dto.UserRegisterResponseDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.exception.AuthUsernameAlreadyRegisterException;
import com.lam.sb_backend.exception.AuthUsernameNotFoundException;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoleService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

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

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(RoleDetails::new)
                .orElseThrow(() -> new AuthUsernameNotFoundException(username, new Throwable("loadUserByUsername")));
    }
}
