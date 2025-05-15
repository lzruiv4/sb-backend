package com.lam.sb_backend.serviceImp;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.domain.model.User;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.repository.IUserRepository;
import com.lam.sb_backend.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserDTO updateUser(UUID userId, User user) {
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUserId(userId);
        UserEntity updatedUser = userRepository.save(IUserMapper.INSTANCE.modelToEntity(user));
        return IUserMapper.INSTANCE.entityToDto(updatedUser);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new org.springframework.security.core.userdetails.User(
//                userEntity.getUsername(),
//                userEntity.getPassword(),
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
//        );
//    }
}
