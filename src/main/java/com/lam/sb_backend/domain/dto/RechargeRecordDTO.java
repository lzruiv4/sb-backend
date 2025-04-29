package com.lam.sb_backend.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record RechargeRecordDTO(
        UUID rechargeRecordId,
        UUID userId,
        int amountRecharge,
        int currentPokeCoin,
        LocalDateTime rechargeAt
) {
}
