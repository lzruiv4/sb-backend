package com.lam.sb_backend.security;

import com.lam.sb_backend.domain.dto.UserRegisterDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public void register(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByUsername(userRegisterDTO.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(userRegisterDTO.username());
        newUserEntity.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
        newUserEntity.setCreatedAt(LocalDateTime.now());
        newUserEntity.setFirstname(userRegisterDTO.firstname());
        newUserEntity.setLastname(userRegisterDTO.lastname());
        newUserEntity.setPokemonCoin(10);
        newUserEntity.getRoles().addAll(userRegisterDTO.roles());
        userRepository.save(newUserEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new org.springframework.security.core.userdetails.User(
//                userEntity.getUsername(),
//                userEntity.getPassword(),
//                userEntity.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
//        );
        return userRepository.findByUsername(username)
                .map(RoleDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User name with " + username + "not found"));
    }
}
