package com.lam.sb_backend.mapper;

import com.lam.sb_backend.domain.dto.RechargeRecordDTO;
import com.lam.sb_backend.domain.entity.RechargeRecordEntity;
import com.lam.sb_backend.domain.model.RechargeRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {IUserMapper.class})
public interface IRechargeRecordMapper {

    IRechargeRecordMapper INSTANCE = Mappers.getMapper(IRechargeRecordMapper.class);

    @Mapping(target = "userId", source = "user.userId")
    RechargeRecordDTO modelToDto(RechargeRecord rechargeRecord);

    @Mapping(target = "user", ignore = true)
    RechargeRecord dtoToModel(RechargeRecordDTO rechargeRecordDTO);

    @Mapping(target = "userEntity", source = "user")
    RechargeRecordEntity modelToEntity(RechargeRecord rechargeRecord);

    @Mapping(target = "user", source = "userEntity")
    RechargeRecord entityToModel(RechargeRecordEntity rechargeRecordEntity);

    /**
     * No need to use currently。
     *
     * We make it only from DTO -> Model -> Entity
     *                  Or Entity -> Model -> DTO
     * */
//    @Mapping(target = "userId", source = "rechargeRecordEntity.userId")
//    RechargeRecordDTO entityToDto(RechargeRecordEntity rechargeRecordEntity);

//    @Mapping(target = "userEntity", ignore = true)
//    RechargeRecordEntity dtoToEntity(RechargeRecordDTO rechargeRecordDTO);
}
