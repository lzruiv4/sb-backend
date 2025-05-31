package com.lam.sb_backend.service;

import com.lam.sb_backend.domain.dto.RechargeRecordDTO;
import com.lam.sb_backend.domain.model.RechargeRecord;

import java.util.List;
import java.util.UUID;

public interface IRechargeRecordService {

    RechargeRecordDTO addRechargeRecord(UUID userId, int amountRecharge);

    RechargeRecordDTO updateRechargeRecord(UUID userId, RechargeRecord rechargeRecord);

    List<RechargeRecordDTO> getAllRechargeRecordByUserId(UUID userId);

    List<RechargeRecordDTO> getAllRechargeRecords();
}
