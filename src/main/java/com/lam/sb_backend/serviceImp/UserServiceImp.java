package com.lam.sb_backend.serviceImp;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.repository.IUserRepository;
import com.lam.sb_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<UserDTO> getUserById(UUID userId) {
        return userRepository.findById(userId).map(IUserMapper.INSTANCE::entityToDto);
    }

    @Override
    public UserDTO addNewUser(UserDTO userDTO) {
        UserEntity savedUserEntity = userRepository.save(IUserMapper.INSTANCE.dtoToEntity(userDTO));
        return IUserMapper.INSTANCE.entityToDto(savedUserEntity);
    }
}
