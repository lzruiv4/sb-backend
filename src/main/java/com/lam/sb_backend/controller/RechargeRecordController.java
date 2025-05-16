package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.RechargeRecordCreateDTO;
import com.lam.sb_backend.domain.dto.RechargeRecordDTO;
import com.lam.sb_backend.service.IRechargeRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rechargeRecords")
@Tag(name="Recharge record", description = "Recharge record history management")
public class RechargeRecordController {

    private final IRechargeRecordService iRechargeRecordService;

    @PostMapping
    public ResponseEntity<RechargeRecordDTO> createRechargeRecord(@RequestBody RechargeRecordCreateDTO rechargeRecordCreateDTO) {
        RechargeRecordDTO rechargeRecordDTOToBeAdd = iRechargeRecordService
                .addRechargeRecord(
                        rechargeRecordCreateDTO.userId(),
                        rechargeRecordCreateDTO.amountRecharge()
                );
        return ResponseEntity.ok().body(rechargeRecordDTOToBeAdd);
    }

    @GetMapping
    public ResponseEntity<List<RechargeRecordDTO>> getAllRechargeRecordsByUserId(@RequestParam("userId") UUID userId) {
        return ResponseEntity.ok(iRechargeRecordService.getAllRechargeRecordByUserId(userId));
    }
}
