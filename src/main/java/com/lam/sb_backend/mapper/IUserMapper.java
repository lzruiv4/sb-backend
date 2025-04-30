package com.lam.sb_backend.mapper;

import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mapping(target = "id", source = "userId")
    UserDTO modelToDto(User user);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "pokemonMap", ignore = true)
    @Mapping(target = "rechargeRecordMap", ignore = true)
    User dtoToModel(UserDTO userDTO);

    UserEntity modelToEntity(User user);

    @Mapping(target = "pokemonMap", ignore = true)
    @Mapping(target = "rechargeRecordMap", ignore = true)
    User entityToModel(UserEntity userEntity);

    @Mapping(target = "id", source = "userId")
    UserDTO entityToDto(UserEntity userEntity);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "password", ignore = true)
    UserEntity dtoToEntity(UserDTO userDTO);

}
