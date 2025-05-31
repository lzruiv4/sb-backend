package com.lam.sb_backend.service;

import com.lam.sb_backend.domain.dto.PokemonRecordDTO;
import com.lam.sb_backend.domain.model.PokemonRecord;

import java.util.List;
import java.util.UUID;

public interface IPokemonRecordService {
    PokemonRecordDTO createPokemonRecord(String pokemonId, UUID userId);

    PokemonRecordDTO changeReleaseToTrue(UUID pokemonRecordId);

    PokemonRecordDTO updatePokemonRecord(UUID pokemonRecordId, PokemonRecord pokemonRecord);

    List<PokemonRecordDTO> getPokemonRecordsByUserId(UUID userId);

    List<PokemonRecordDTO> getAllPokemonRecords();
}
