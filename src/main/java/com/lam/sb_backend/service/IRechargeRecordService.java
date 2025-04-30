package com.lam.sb_backend.service;

import com.lam.sb_backend.domain.dto.RechargeRecordDTO;

import java.util.List;
import java.util.UUID;

public interface IRechargeRecordService {

    RechargeRecordDTO addRechargeRecord(UUID userId, int amountRecharge);

    List<RechargeRecordDTO> getAllRechargeRecordByUserId(UUID userId);
}
