package com.lam.sb_backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private UUID userId;
    private String username;
    private String password;
    private LocalDate createdAt;
    private String firstname;
    private String lastname;
    private int pokemonCoin;
    private Map<LocalDate, List<PokemonRecord>> pokemonMap;
    private Map<LocalDate, List<RechargeRecord>> rechargeRecordMap;
}
