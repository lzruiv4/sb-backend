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
@Table(name = "recharge_record")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RechargeRecordEntity {
    @Id
    @UuidGenerator
    @Column(columnDefinition = "UUID", nullable = false, updatable = false)
    private UUID rechargeRecordId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private UserEntity userEntity;
    private int amountRecharge;
    private int currentPokeCoin;
    private LocalDate rechargeAt;
}
