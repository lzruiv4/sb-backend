package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.*;
import com.lam.sb_backend.domain.enums.Role;
import com.lam.sb_backend.repository.IRechargeRecordRepository;
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
class RechargeRecordControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IRechargeRecordRepository iRechargeRecordRepository;

    private HttpEntity<Void> withToken;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        // delete db in every test
        iRechargeRecordRepository.deleteAll();

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
    }

    @Test
    void testRechargeRecordCreateAndGetAllRechargeRecords() {
        // New user have 10 coins
        assertEquals(10, userDTO.pokemonCoin());

        // Add new recharge record
        RechargeRecordCreateDTO rechargeRecordCreateDTO = new RechargeRecordCreateDTO(userDTO.id(), 1);
        RechargeRecordDTO rechargeRecordDTO = testRestTemplate.exchange(
                "/api/rechargeRecords",
                HttpMethod.POST,
                new HttpEntity<>(rechargeRecordCreateDTO, withToken.getHeaders()),
                RechargeRecordDTO.class
        ).getBody();
        assertNotNull(rechargeRecordDTO.id());
        assertEquals(1, rechargeRecordDTO.amountRecharge());
        assertEquals(11, rechargeRecordDTO.currentPokemonCoin());
        assertEquals(userDTO.id(), rechargeRecordDTO.userId());
        assertNotNull(rechargeRecordDTO.rechargeAt());

        userDTO = testRestTemplate.exchange(
                        "/api/users/" + userDTO.id(),
                        HttpMethod.GET,
                        withToken,
                        UserDTO.class)
                .getBody();
        assertEquals(11, userDTO.pokemonCoin());

        // Add one more recharge record
        testRestTemplate.exchange(
                "/api/rechargeRecords",
                HttpMethod.POST,
                new HttpEntity<>(rechargeRecordCreateDTO, withToken.getHeaders()),
                RechargeRecordDTO.class
        );
        int recordMenge = testRestTemplate.exchange(
                "/api/rechargeRecords?userId=" + userDTO.id(),
                HttpMethod.GET,
                withToken,
                new ParameterizedTypeReference<List<RechargeRecordDTO>>() {})
                .getBody()
                .size();
        assertEquals(2, recordMenge);

    }
}