package com.lam.sb_backend.domain.dto;

import java.util.UUID;

public record RechargeRecordCreateDTO(
        UUID userId,
        int amountRecharge
) {
}
