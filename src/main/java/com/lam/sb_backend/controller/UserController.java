package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@Tag(name="User", description = "User API")
public class UserController {

    private final IUserService iUserService;

    @GetMapping("/{userId}")
    @Operation(summary = "Get user info by userId", description = "Returns basic user information")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") UUID userId){
        UserDTO userDTO = iUserService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns all users information, only for manager")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(iUserService.getAllUsers());
    }

//    @PostMapping
//    @Operation(summary = "Create a new user", description = "Create an user by basic information")
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
//        UserDTO userToBeAdd = iUserService.addNewUser(IUserMapper.INSTANCE.dtoToModel(userDTO));
//        return ResponseEntity.status(HttpStatus.CREATED).body(userToBeAdd);
//    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user info", description = "Returns updated user information")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") UUID userId, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(iUserService.updateUser(userId, IUserMapper.INSTANCE.dtoToModel(userDTO)));
    }
}
