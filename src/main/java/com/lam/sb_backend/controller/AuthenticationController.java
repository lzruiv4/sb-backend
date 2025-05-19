package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.TokenDTO;
import com.lam.sb_backend.domain.dto.UserLoginDTO;
import com.lam.sb_backend.domain.dto.UserRegisterDTO;
import com.lam.sb_backend.domain.dto.UserRegisterResponseDTO;
import com.lam.sb_backend.security.JwtService;
import com.lam.sb_backend.security.RoleDetails;
import com.lam.sb_backend.security.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name="Authentication", description = "Authentication: User or manager")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return ResponseEntity.ok(roleService.register(userRegisterDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password())
        );
        return ResponseEntity.ok(new TokenDTO(
                ((RoleDetails) auth.getPrincipal()).getUserId(),
                jwtService.generateToken(auth)
        ));
    }
}
