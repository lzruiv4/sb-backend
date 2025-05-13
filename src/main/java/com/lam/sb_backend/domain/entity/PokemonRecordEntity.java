package com.lam.sb_backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="sb_pokemon_record")
@NoArgsConstructor
@Getter
@Setter
public class PokemonRecordEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID pokemonCaptureRecordId;
    private String pokemonId;
    private LocalDateTime captureTime;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;
    private boolean isRelease;

}

