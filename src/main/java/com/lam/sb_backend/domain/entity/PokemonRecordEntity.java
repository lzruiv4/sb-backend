package com.lam.sb_backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="pokemonRecord")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonRecordEntity {

    @Id
    private UUID recordId;
    private String pokemonId;
    private LocalDate captureTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private UserEntity user;
    private boolean isRelease;

}

