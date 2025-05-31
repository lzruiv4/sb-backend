package com.lam.sb_backend.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record RechargeRecordCreateDTO(
        @NotNull(message = "User ID can not be null")
        UUID userId,
        @Positive(message = "Amount can not be nagetiv")
        int amountRecharge
) {
}
