package com.lam.sb_backend.mapper;

import com.lam.sb_backend.domain.dto.PokemonRecordDTO;
import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.PokemonRecordEntity;
import com.lam.sb_backend.domain.model.PokemonRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class IPokemonRecordMapperTest {

    PokemonRecord pokemonRecord;
    PokemonRecordEntity pokemonRecordEntity;
    PokemonRecordDTO pokemonRecordDTO;

    UUID RECORD_ID;
    LocalDateTime testTime = LocalDateTime.now();
    UserDTO userDTO = new UserDTO(
            UUID.randomUUID(),
            "username",
            testTime,
            "firstname",
            "lastname",
            10
    );


    @BeforeEach
    void setUp() {
        RECORD_ID = UUID.randomUUID();
        pokemonRecord = new PokemonRecord();
        pokemonRecordEntity = new PokemonRecordEntity();

        pokemonRecordDTO = new PokemonRecordDTO(
                RECORD_ID,
                "pokemonId",
                testTime,
                userDTO.id(),
                false
        );
    }

    @Test
    void entityToDTO() {
        pokemonRecordEntity = IPokemonRecordMapper.INSTANCE.dtoToEntity(pokemonRecordDTO);
        assertNull(pokemonRecordEntity.getUserEntity()); // Here is null, because user is ignore.
        pokemonRecordEntity.setUserEntity(IUserMapper.INSTANCE.dtoToEntity(userDTO));
        pokemonRecordDTO = IPokemonRecordMapper.INSTANCE.entityToDto(pokemonRecordEntity);
        assertEquals(userDTO.id(), pokemonRecordDTO.userId());
        assertEquals(RECORD_ID, pokemonRecordDTO.recordId());
        assertEquals("pokemonId", pokemonRecordDTO.pokemonId());
        assertEquals(testTime, pokemonRecordDTO.captureTime());
        assertFalse(pokemonRecordDTO.isRelease());
    }

    // TODO : Add more tests
}
