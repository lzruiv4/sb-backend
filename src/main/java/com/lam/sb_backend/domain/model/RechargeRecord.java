package com.lam.sb_backend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class RechargeRecord {
    private UUID rechargeRecordId;
    private User user;
    private int amountRecharge;
    private int currentPokemonCoin;
    private LocalDateTime rechargeAt;
}
