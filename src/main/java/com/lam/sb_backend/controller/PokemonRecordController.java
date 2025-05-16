package com.lam.sb_backend.controller;

import com.lam.sb_backend.domain.dto.PokemonRecordCreateDTO;
import com.lam.sb_backend.domain.dto.PokemonRecordDTO;
import com.lam.sb_backend.service.IPokemonRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PokemonRecordDTO> createPokemonRecord(@RequestBody PokemonRecordCreateDTO pokemonRecordCreateDTO) {
        return ResponseEntity.ok()
                .body(iPokemonRecordService.createPokemonRecord(
                        pokemonRecordCreateDTO.pokemonId(),
                        pokemonRecordCreateDTO.userId()
                ));
    }

    @GetMapping
    public ResponseEntity<List<PokemonRecordDTO>> getPokemonRecordsByUserId(@RequestParam("userId") UUID userId) {
        return ResponseEntity.ok(iPokemonRecordService.getPokemonRecordsByUserId(userId));
    }

    @PutMapping("/{pokemonRecordId}")
    public ResponseEntity<PokemonRecordDTO> updatePokemonRecordStatus(@PathVariable("pokemonRecordId") UUID pokemonRecordId) {
        return ResponseEntity.ok(iPokemonRecordService.changeReleaseToTrue(pokemonRecordId));
    }
}
