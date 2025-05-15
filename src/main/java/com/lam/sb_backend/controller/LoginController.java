package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.AuthResponseDTO;
import com.lam.sb_backend.domain.dto.LoginRequestDTO;
import com.lam.sb_backend.domain.dto.RegisterRequestDTO;
import com.lam.sb_backend.serviceImp.JwtService;
import com.lam.sb_backend.serviceImp.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request) {
        roleService.register(request);
        return ResponseEntity.ok("Registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        String token = jwtService.generateToken(auth);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
