package com.lam.sb_backend.mapper;

import com.lam.sb_backend.domain.dto.PokemonRecordDTO;
import com.lam.sb_backend.domain.entity.PokemonRecordEntity;
import com.lam.sb_backend.domain.model.PokemonRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {IUserMapper.class})
public interface IPokemonRecordMapper {
    IPokemonRecordMapper INSTANCE = Mappers.getMapper(IPokemonRecordMapper.class);

    @Mapping(target = "id", source = "pokemonCaptureRecordId")
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "isRelease", source = "release")
    PokemonRecordDTO modelToDto(PokemonRecord pokemonRecord);

    @Mapping(target = "pokemonCaptureRecordId", source = "id")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "release", source = "isRelease")
    PokemonRecord dtoToModel(PokemonRecordDTO pokemonRecordDTO);

    @Mapping(target = "userEntity", source = "user")
    @Mapping(target = "release", source = "release")
    PokemonRecordEntity modelToEntity(PokemonRecord pokemonRecord);

    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "release", source = "release")
    PokemonRecord entityToModel(PokemonRecordEntity pokemonRecordEntity);

    @Mapping(target = "id", source = "pokemonCaptureRecordId")
    @Mapping(target = "userId", source = "userEntity.userId")
    @Mapping(target = "isRelease", source = "release")
    PokemonRecordDTO entityToDto(PokemonRecordEntity pokemonRecordEntity);

    @Mapping(target = "pokemonCaptureRecordId", source = "id")
    @Mapping(target = "userEntity", ignore = true)
    @Mapping(target = "release", source = "isRelease")
    PokemonRecordEntity dtoToEntity(PokemonRecordDTO pokemonRecordDTO);
}
