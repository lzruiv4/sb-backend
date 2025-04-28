package com.lam.sb_backend.mapper;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IUserMapperTest {

    UserDTO userDto;

    UserEntity userEntity;

    User user;

    @BeforeEach
    void setUp() {
        userDto = new UserDTO(
                "userId",
                "username",
                LocalDate.of(2023, 5, 20),
                "firstname",
                "lastname",
                10
        );

        userEntity = new UserEntity();
        user = new User();
    }

    @Test
    void entityToDto() {
        userEntity = IUserMapper.INSTANCE.dtoToEntity(userDto);
        assertNull(userEntity.getPassword());
        UserDTO result = IUserMapper.INSTANCE.entityToDto(userEntity);
        assertEquals(10, result.pokemonCoin());
    }

    @Test
    void dtoToEntity() {
        UserEntity userEntity = IUserMapper.INSTANCE.dtoToEntity(userDto);
        assertEquals("userId", userEntity.getUserId());
        assertNull(userEntity.getPassword());
        assertEquals(2023, userEntity.getCreatedAt().getYear());
        assertEquals(5, userEntity.getCreatedAt().getMonthValue());
        assertEquals(20, userEntity.getCreatedAt().getDayOfMonth());
    }

    @Test
    void dtoToModel() {
        user = IUserMapper.INSTANCE.dtoToModel(userDto);
        assertNull(user.getPassword());
        assertNull(user.getPokemonMap());
    }

    @Test
    void modelToDto() {
        user = IUserMapper.INSTANCE.dtoToModel(userDto);
        assertEquals("userId", user.getUserId());
        UserDTO result = IUserMapper.INSTANCE.modelToDto(user);
        assertEquals("userId", result.userId());
    }

    @Test
    void modelToEntity() {
        user = IUserMapper.INSTANCE.dtoToModel(userDto);
        assertNull(user.getPassword());
        assertNull(user.getPokemonMap());
        user.setPassword("password");

        userEntity = IUserMapper.INSTANCE.modelToEntity(user);
        assertEquals("userId", userEntity.getUserId());
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
        assertEquals("userId", userEntity.getUserId());
        assertEquals("username", userEntity.getUsername());
        assertEquals("firstname", userEntity.getFirstname());
        assertEquals("lastname", userEntity.getLastname());
        assertEquals("password", userEntity.getPassword());
        assertNull(user.getPokemonMap());
    }
}