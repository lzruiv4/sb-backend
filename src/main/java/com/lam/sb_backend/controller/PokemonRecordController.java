package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.PokemonRecordCreateDTO;
import com.lam.sb_backend.domain.dto.PokemonRecordDTO;
import com.lam.sb_backend.service.IPokemonRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pokemonRecords")
@Tag(name="Pokemon record", description = "Pokemon record management")
public class PokemonRecordController {

    private final IPokemonRecordService iPokemonRecordService;

    @PostMapping
    @Operation(summary = "Create new pokemon capture record", description = "Returns new pokemon capture record")
    public ResponseEntity<PokemonRecordDTO> createPokemonRecord(@RequestBody PokemonRecordCreateDTO pokemonRecordCreateDTO) {
        return ResponseEntity.ok()
                .body(iPokemonRecordService.createPokemonRecord(
                        pokemonRecordCreateDTO.pokemonId(),
                        pokemonRecordCreateDTO.userId()
                ));
    }

    @GetMapping(params = "userId")
    @Operation(summary = "Get all pokemon records by userId", description = "Returns all pokemon records by userId")
    public ResponseEntity<List<PokemonRecordDTO>> getPokemonRecordsByUserId(@RequestParam("userId") UUID userId) {
        return ResponseEntity.ok(iPokemonRecordService.getPokemonRecordsByUserId(userId));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all pokemon records", description = "Returns all pokemon records, only for manager")
    public ResponseEntity<List<PokemonRecordDTO>> getAllPokemonRecords(){
        return ResponseEntity.ok(iPokemonRecordService.getAllPokemonRecords());
    }

    @PutMapping("/{pokemonRecordId}")
    @Operation(summary = "Update a pokemon record", description = "Set pokemon release to true")
    public ResponseEntity<PokemonRecordDTO> updatePokemonRecordStatus(@PathVariable("pokemonRecordId") UUID pokemonRecordId) {
        return ResponseEntity.ok(iPokemonRecordService.changeReleaseToTrue(pokemonRecordId));
    }
}
