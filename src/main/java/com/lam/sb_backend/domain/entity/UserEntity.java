package com.lam.sb_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sb_user")
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
//    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private String firstname;
    private String lastname;
//    @Column(name = "pokemon_coin")
    private int pokemonCoin;
}
