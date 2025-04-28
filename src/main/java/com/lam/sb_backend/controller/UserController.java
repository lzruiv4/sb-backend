package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId){
        Optional<UserDTO> userDTOOptional = iUserService.getUserById(userId);
        return userDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userToBeAdd = iUserService.addNewUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userToBeAdd);
    }
}
