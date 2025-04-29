package com.lam.sb_backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="pokemonRecord")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonRecordEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID recordId;
    private String pokemonId;
    private LocalDateTime captureTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private UserEntity userEntity;
    private boolean isRelease;

}

