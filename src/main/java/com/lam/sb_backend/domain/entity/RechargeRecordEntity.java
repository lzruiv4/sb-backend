package com.lam.sb_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sb_recharge_record")
@NoArgsConstructor
@Getter
@Setter
public class RechargeRecordEntity {
    @Id
    @UuidGenerator
    @Column(columnDefinition = "UUID", nullable = false, updatable = false)
    private UUID rechargeRecordId;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;
    private int amountRecharge;
    private int currentPokemonCoin;
    private LocalDateTime rechargeAt;
}
