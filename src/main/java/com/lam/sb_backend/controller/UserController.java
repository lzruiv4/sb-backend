package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.dto.UserPasswordDTO;
import com.lam.sb_backend.domain.dto.UserRegisterDTO;
import com.lam.sb_backend.domain.dto.UserRegisterResponseDTO;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@Tag(name="User", description = "User API")
public class UserController {

    private final IUserService iUserService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add new user", description = "Returns new user information, only for admin")
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return ResponseEntity.ok(iUserService.register(userRegisterDTO));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user info by userId", description = "Returns basic user information")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") UUID userId){
        UserDTO userDTO = iUserService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users", description = "Returns all users information, only for manager")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(iUserService.getAllUsers());
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user info", description = "Returns updated user information")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") UUID userId, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(iUserService.updateUser(userId, IUserMapper.INSTANCE.dtoToModel(userDTO)));
    }

    @PutMapping("/{userId}/passwordUpdate")
    @Operation(summary = "Update user password", description = "Returns updated password successful")
    public ResponseEntity<String> updateUserPassword(
            @PathVariable("userId") UUID userId, @RequestBody UserPasswordDTO userPasswordDTO
    ){
        iUserService.updatePassword(userId, userPasswordDTO.oldPassword(), userPasswordDTO.newPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

    // TODO: add forget password
}
