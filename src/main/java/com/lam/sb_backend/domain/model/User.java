package com.lam.sb_backend.domain.model;

import com.lam.sb_backend.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private UUID userId;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private String firstname;
    private String lastname;
    private int pokemonCoin;
    private Set<Role> roles;
    private Map<LocalDate, List<PokemonRecord>> pokemonMap;
    private Map<LocalDate, List<RechargeRecord>> rechargeRecordMap;
}
