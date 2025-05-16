package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.AuthResponseDTO;
import com.lam.sb_backend.domain.dto.LoginRequestDTO;
import com.lam.sb_backend.domain.dto.RegisterRequestDTO;
import com.lam.sb_backend.util.auth.JwtService;
import com.lam.sb_backend.util.auth.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

//    @Autowired
    private final AuthenticationManager authenticationManager;
//    @Autowired
    private final RoleService roleService;
//    @Autowired
    private final JwtService jwtService;

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
