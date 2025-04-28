package com.lam.sb_backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonRecord {
    private UUID recordId;
    private String pokemonId;
    private LocalDate captureTime;
    private User user;
    private boolean isRelease;
}
