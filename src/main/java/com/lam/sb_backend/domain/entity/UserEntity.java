package com.lam.sb_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pokemon_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @UuidGenerator
    @Column(columnDefinition = "UUID", nullable = false, updatable = false)
    private UUID userId;
    private String username;
    private String password;
    private LocalDate createdAt;
    private String firstname;
    private String lastname;
    private int pokemonCoin;
}
