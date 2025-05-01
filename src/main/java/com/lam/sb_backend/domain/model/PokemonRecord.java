package com.lam.sb_backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonRecord {
    private UUID pokemonCaptureRecordId;
    private String pokemonId;
    private LocalDateTime captureTime;
    private User user;
    private boolean isRelease;
}
