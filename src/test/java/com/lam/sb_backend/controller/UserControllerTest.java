package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.*;
import com.lam.sb_backend.domain.enums.Role;
import com.lam.sb_backend.repository.IUserRepository;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IUserRepository userRepository;

    private HttpEntity<Void> withToken;

    private TokenDTO adminTokenDTO;

    @BeforeEach
    void setUp() {
        // delete db in every test
        userRepository.deleteAll();
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
        adminTokenDTO = testRestTemplate
                .postForObject(
                        "/api/auth/login",
                        new UserLoginDTO(first_admin_user.username(), first_admin_user.password()),
                        TokenDTO.class
                );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminTokenDTO.token());
        withToken = new HttpEntity<>(headers);
    }

    @Test
    void testAdminIsSaved(){
        assertEquals(1, Objects.requireNonNull(testRestTemplate.exchange(
                "/api/users",
                HttpMethod.GET,
                withToken,
                new ParameterizedTypeReference<List<UserDTO>>() {}
        ).getBody()).size());
    }

    @Test
    void testAddNewUser() {
        // add a user to database
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(
                "test",
                "firstname",
                "lastname",
                "123",
                roles
        );

        UserRegisterResponseDTO newUserRegisterResponseDTO = testRestTemplate
                .postForObject(
                        "/api/auth/register",
                        userRegisterDTO,
                        UserRegisterResponseDTO.class
                );

        // Get the token after login
        TokenDTO tokenDTO = testRestTemplate
                .postForObject(
                        "/api/auth/login",
                        new UserLoginDTO(userRegisterDTO.username(), userRegisterDTO.password()),
                        TokenDTO.class
                );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenDTO.token());

        UserDTO userDTO = testRestTemplate.exchange(
                "/api/users/" + newUserRegisterResponseDTO.userId(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                UserDTO.class
        ).getBody();

        assertNotNull(userDTO);
        assertEquals(newUserRegisterResponseDTO.userId(), userDTO.id());
        assertEquals("test", userDTO.username());
        assertEquals("firstname", userDTO.firstname());
        assertEquals("lastname", userDTO.lastname());
        assertEquals(10, userDTO.pokemonCoin());
        assertEquals(Role.ROLE_USER, userDTO.roles().toArray()[0]);
    }

    @Test
    void testUpdateUserInfo() {

        UserDTO userDTO = testRestTemplate.exchange(
                "/api/users/" + adminTokenDTO.userId(),
                HttpMethod.GET,
                withToken,
                UserDTO.class
        ).getBody();

        UserDTO newUserDTO = new UserDTO(
                userDTO.id(),
                "new_username",
                "new_password",
                userDTO.createdAt(),
                "new_first_name",
                "new_last_name",
                1,
                userDTO.roles());

        UserDTO resultDTO = testRestTemplate.exchange(
                "/api/users/" + newUserDTO.id(),
                HttpMethod.PUT,
                new HttpEntity<>(newUserDTO, withToken.getHeaders()),
                UserDTO.class).getBody();

        assertEquals(adminTokenDTO.userId(), resultDTO.id());
        assertEquals("new_username", resultDTO.username());
        assertEquals("new_first_name", resultDTO.firstname());
        assertEquals("new_last_name", resultDTO.lastname());
        assertEquals(1, resultDTO.pokemonCoin());
        assertEquals(Role.ROLE_ADMIN, resultDTO.roles().toArray()[0]);
    }
}