package com.lam.sb_backend.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.util.UUID;

public record RechargeRecordDTO(
        UUID id,
        @NotNull(message = "userId can not be null")
        UUID userId,
        @Positive(message = "amount should bigger as 0")
        int amountRecharge,
        @PositiveOrZero(message = "currentPokemonCoin can not be negativ")
        int currentPokemonCoin,
        LocalDateTime rechargeAt
) {
}
