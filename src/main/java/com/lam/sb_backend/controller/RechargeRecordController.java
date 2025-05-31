package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.RechargeRecordCreateDTO;
import com.lam.sb_backend.domain.dto.RechargeRecordDTO;
import com.lam.sb_backend.mapper.IRechargeRecordMapper;
import com.lam.sb_backend.service.IRechargeRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "Create new recharge record", description = "Returns new recharge record")
    public ResponseEntity<RechargeRecordDTO> createRechargeRecord(
            @RequestBody RechargeRecordCreateDTO rechargeRecordCreateDTO
    ) {
        RechargeRecordDTO rechargeRecordDTOToBeAdd = iRechargeRecordService
                .addRechargeRecord(
                        rechargeRecordCreateDTO.userId(),
                        rechargeRecordCreateDTO.amountRecharge()
                );
        return ResponseEntity.ok().body(rechargeRecordDTOToBeAdd);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update recharge record", description = "Returns updated recharge record")
    public ResponseEntity<RechargeRecordDTO> updateRechargeRecord(
            @PathVariable("userId") UUID userId,
            @RequestBody @Valid RechargeRecordDTO rechargeRecordDTO
    ){
        return ResponseEntity.ok(iRechargeRecordService.updateRechargeRecord(
                userId,
                IRechargeRecordMapper.INSTANCE.dtoToModel(rechargeRecordDTO))
        );
    }

    @GetMapping("/{userId}")// here must be added params
    @Operation(summary = "Get all recharge records by userId", description = "Returns all recharge records by userId")
    public ResponseEntity<List<RechargeRecordDTO>> getAllRechargeRecordsByUserId(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(iRechargeRecordService.getAllRechargeRecordByUserId(userId));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all recharge records", description = "Returns all recharge records, only for manager")
    public ResponseEntity<List<RechargeRecordDTO>> getAllRechargeRecords(){
        return ResponseEntity.ok(iRechargeRecordService.getAllRechargeRecords());
    }
}
