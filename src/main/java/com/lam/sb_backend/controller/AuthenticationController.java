package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.TokenDTO;
import com.lam.sb_backend.domain.dto.UserLoginDTO;
import com.lam.sb_backend.domain.dto.UserRegisterDTO;
import com.lam.sb_backend.security.JwtService;
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

//    @Autowired
    private final AuthenticationManager authenticationManager;
//    @Autowired
    private final RoleService roleService;
//    @Autowired
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO request) {
        roleService.register(request);
        return ResponseEntity.ok("Registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserLoginDTO request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        String token = jwtService.generateToken(auth);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
