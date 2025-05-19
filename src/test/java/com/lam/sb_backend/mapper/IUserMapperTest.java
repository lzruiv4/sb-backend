package com.lam.sb_backend.mapper;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.domain.enums.Role;
import com.lam.sb_backend.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class IUserMapperTest {

    UserDTO userDto;

    UserEntity userEntity;

    User user;

    UUID id;

    LocalDateTime testTime = LocalDateTime.now();

    Set<Role> roles = new HashSet<>();

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        roles.add(Role.ROLE_USER);
        userDto = new UserDTO(
                id,
                "username",
                "password",
                testTime,
                "firstname",
                "lastname",
                10,
                roles
        );

        userEntity = new UserEntity();
        user = new User();
    }

    @Test
    void entityToDto() {
        userEntity = IUserMapper.INSTANCE.dtoToEntity(userDto);
        assertEquals("password", userEntity.getPassword());
        UserDTO result = IUserMapper.INSTANCE.entityToDto(userEntity);
        assertEquals(10, result.pokemonCoin());
    }

    @Test
    void dtoToEntity() {
        UserEntity userEntity = IUserMapper.INSTANCE.dtoToEntity(userDto);
        assertEquals(id, userEntity.getUserId());
        assertEquals("password", userEntity.getPassword());
        assertEquals(testTime.getYear(), userEntity.getCreatedAt().getYear());
        assertEquals(testTime.getMonthValue(), userEntity.getCreatedAt().getMonthValue());
        assertEquals(testTime.getDayOfMonth(), userEntity.getCreatedAt().getDayOfMonth());
    }

    @Test
    void dtoToModel() {
        user = IUserMapper.INSTANCE.dtoToModel(userDto);
        assertEquals("password", user.getPassword());
        assertNull(user.getPokemonMap());
    }

    @Test
    void modelToDto() {
        user = IUserMapper.INSTANCE.dtoToModel(userDto);
        assertEquals(id, user.getUserId());
        UserDTO result = IUserMapper.INSTANCE.modelToDto(user);
        assertEquals(id, result.id());
    }

    @Test
    void modelToEntity() {
        user = IUserMapper.INSTANCE.dtoToModel(userDto);
        assertEquals("password", user.getPassword());
        assertNull(user.getPokemonMap());
        user.setPassword("password");

        userEntity = IUserMapper.INSTANCE.modelToEntity(user);
        assertEquals(id, userEntity.getUserId());
        assertEquals("username", userEntity.getUsername());
        assertEquals("firstname", userEntity.getFirstname());
        assertEquals("lastname", userEntity.getLastname());
        assertEquals("password", userEntity.getPassword());
    }

    @Test
    void entityToModel() {
        userEntity = IUserMapper.INSTANCE.dtoToEntity(userDto);
        userEntity.setPassword("password");

        user = IUserMapper.INSTANCE.entityToModel(userEntity);
        assertEquals(id, userEntity.getUserId());
        assertEquals("username", userEntity.getUsername());
        assertEquals("firstname", userEntity.getFirstname());
        assertEquals("lastname", userEntity.getLastname());
        assertEquals("password", userEntity.getPassword());
        assertNull(user.getPokemonMap());
    }
}