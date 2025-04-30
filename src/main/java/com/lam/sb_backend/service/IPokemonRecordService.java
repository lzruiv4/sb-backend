package com.lam.sb_backend.service;

import com.lam.sb_backend.domain.dto.PokemonRecordDTO;
import com.lam.sb_backend.domain.model.PokemonRecord;

import java.util.List;
import java.util.UUID;

public interface IPokemonRecordService {
    PokemonRecordDTO createPokemonRecord(PokemonRecord pokemonRecord, UUID userId);

    PokemonRecordDTO changeReleaseToTrue(UUID pokemonRecordId);

    List<PokemonRecordDTO> getPokemonRecordsByUserId(UUID userId);
}
