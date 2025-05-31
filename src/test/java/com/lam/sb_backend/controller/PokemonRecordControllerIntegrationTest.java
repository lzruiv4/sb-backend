package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.*;
import com.lam.sb_backend.domain.enums.Role;
import com.lam.sb_backend.repository.IPokemonRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PokemonRecordControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IPokemonRecordRepository iPokemonRecordRepository;

    private HttpEntity<Void> withToken;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        // delete db in every test
        iPokemonRecordRepository.deleteAll();

        // add an admin user to database
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        UserRegisterDTO first_admin_user = new UserRegisterDTO(
                "username",
                "firstname",
                "lastname",
                "123",
                roles
        );

        testRestTemplate
                .postForObject(
                        "/api/auth/register",
                        first_admin_user,
                        UserRegisterResponseDTO.class
                );

        // Get the token with admin
        TokenDTO adminTokenDTO = testRestTemplate
                .postForObject(
                        "/api/auth/login",
                        new UserLoginDTO(first_admin_user.username(), first_admin_user.password()),
                        TokenDTO.class
                );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminTokenDTO.token());
        withToken = new HttpEntity<>(headers);

        userDTO = testRestTemplate.exchange(
                        "/api/users/" + adminTokenDTO.userId(),
                        HttpMethod.GET,
                        withToken,
                        UserDTO.class)
                .getBody();

        // Add a pokemon record
        testRestTemplate.exchange(
                "/api/pokemonRecords",
                HttpMethod.POST,
                new HttpEntity<>(
                        new PokemonRecordCreateDTO("1", userDTO.id()),
                        withToken.getHeaders()
                ),
                PokemonRecordDTO.class
        );
    }

    @Test
    void testCreateAndGetAllPokemonRecords() {
        // Add new pokemon record
        PokemonRecordDTO pokemonRecordDTO = testRestTemplate.exchange(
                "/api/pokemonRecords",
                HttpMethod.POST,
                new HttpEntity<>(new PokemonRecordCreateDTO("2", userDTO.id()), withToken.getHeaders()),
                PokemonRecordDTO.class
        ).getBody();
        assertNotNull(pokemonRecordDTO.id());
        assertEquals("2", pokemonRecordDTO.pokemonId());
        assertNotNull(pokemonRecordDTO.captureTime());
        assertEquals(userDTO.id(), pokemonRecordDTO.userId());
        assertFalse(pokemonRecordDTO.isRelease());

        int recordMenge = testRestTemplate.exchange(
                        "/api/pokemonRecords?userId=" + userDTO.id(),
                        HttpMethod.GET,
                        withToken,
                        new ParameterizedTypeReference<List<PokemonRecordDTO>>() {})
                .getBody()
                .size();
        assertEquals(2, recordMenge);
    }

    @Test
    void testUpdateAPokemonRecord(){
        PokemonRecordDTO pokemonRecordDTO = testRestTemplate.exchange(
                        "/api/pokemonRecords/" + userDTO.id(),
                        HttpMethod.GET,
                        withToken,
                        new ParameterizedTypeReference<List<PokemonRecordDTO>>() {}
                )
                .getBody().get(0);

        // Update pokemon record release to true
        PokemonRecordDTO result = testRestTemplate.exchange(
                "/api/pokemonRecords/" + pokemonRecordDTO.id() + "/release",
                HttpMethod.PUT,
                withToken,
                PokemonRecordDTO.class
        ).getBody();
        assertTrue(result.isRelease());
    }
}