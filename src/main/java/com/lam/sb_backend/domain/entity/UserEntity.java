package com.lam.sb_backend.domain.entity;

import com.lam.sb_backend.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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
    private LocalDateTime createdAt;
    private String firstname;
    private String lastname;
    private int pokemonCoin;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
}
