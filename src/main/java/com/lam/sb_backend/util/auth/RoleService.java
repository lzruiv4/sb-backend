package com.lam.sb_backend.util.auth;

import com.lam.sb_backend.domain.dto.UserRegisterDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    public void register(UserRegisterDTO req) {
        if (userRepository.findByUsername(req.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(req.username());
        newUserEntity.setPassword(passwordEncoder.encode(req.password()));
        newUserEntity.setCreatedAt(LocalDateTime.now());
        newUserEntity.setFirstname(req.firstname());
        newUserEntity.setLastname(req.lastname());
        newUserEntity.setPokemonCoin(10);
        newUserEntity.getRoles().add("ROLE_USER");
        userRepository.save(newUserEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        );
    }
}
