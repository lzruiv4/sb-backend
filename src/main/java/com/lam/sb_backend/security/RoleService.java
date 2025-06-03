package com.lam.sb_backend.security;

import com.lam.sb_backend.domain.dto.UserRegisterDTO;
import com.lam.sb_backend.domain.dto.UserRegisterResponseDTO;
import com.lam.sb_backend.serviceImp.UserServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RoleService implements UserDetailsService {

    @Autowired
    private UserServiceImp iUserServiceImp;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UserRegisterResponseDTO register(UserRegisterDTO userRegisterDTO) {
        return iUserServiceImp.register(userRegisterDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return iUserServiceImp.loadUserByUsername(username);
    }
}
