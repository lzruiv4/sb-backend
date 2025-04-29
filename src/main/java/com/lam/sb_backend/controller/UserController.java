package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId){
        UserDTO userDTO = iUserService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(iUserService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userToBeAdd = iUserService.addNewUser(IUserMapper.INSTANCE.dtoToModel(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(userToBeAdd);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(iUserService.updateUser(IUserMapper.INSTANCE.dtoToModel(userDTO)));
    }
}
