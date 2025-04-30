package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.RechargeRecordDTO;
import com.lam.sb_backend.service.IRechargeRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rechargeRecords")
public class RechargeRecordController {

    private final IRechargeRecordService iRechargeRecordService;

    @PostMapping
    public ResponseEntity<RechargeRecordDTO> createRechargeRecord(@RequestBody RechargeRecordDTO rechargeRecordDTO) {
        RechargeRecordDTO rechargeRecordDTOToBeAdd = iRechargeRecordService
                .addRechargeRecord(rechargeRecordDTO.userId(), rechargeRecordDTO.amountRecharge());
        return ResponseEntity.ok().body(rechargeRecordDTOToBeAdd);
    }

    @GetMapping
    public ResponseEntity<List<RechargeRecordDTO>> getAllRechargeRecordsByUserId(@RequestParam UUID userId) {
        return ResponseEntity.ok(iRechargeRecordService.getAllRechargeRecordByUserId(userId));
    }
}
