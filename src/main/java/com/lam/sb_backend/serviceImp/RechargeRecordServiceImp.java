package com.lam.sb_backend.serviceImp;

import com.lam.sb_backend.domain.dto.RechargeRecordDTO;
import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.RechargeRecordEntity;
import com.lam.sb_backend.domain.model.User;
import com.lam.sb_backend.exception.CoinRechargeInvalidException;
import com.lam.sb_backend.mapper.IRechargeRecordMapper;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.repository.IRechargeRecordRepository;
import com.lam.sb_backend.service.IRechargeRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RechargeRecordServiceImp implements IRechargeRecordService {

    private final IRechargeRecordRepository rechargeRecordRepository;

    private final UserServiceImp userServiceImp;

    @Override
    public RechargeRecordDTO addRechargeRecord(UUID userId, int amountRecharge) {
        if(amountRecharge <= 0) {
            throw new CoinRechargeInvalidException(new Throwable("addRechargeRecord"));
        }

        UserDTO userDTO = userServiceImp.getUserById(userId);
        User user = IUserMapper.INSTANCE.dtoToModel(userDTO);

        RechargeRecordEntity rechargeRecordEntity = new RechargeRecordEntity();
        rechargeRecordEntity.setAmountRecharge(amountRecharge);
        int currentPokemonCoin = user.getPokemonCoin() + amountRecharge;
        user.setPokemonCoin(currentPokemonCoin);
        rechargeRecordEntity.setCurrentPokemonCoin(currentPokemonCoin);
        rechargeRecordEntity.setUserEntity(IUserMapper.INSTANCE.modelToEntity(user));
        rechargeRecordEntity.setRechargeAt(LocalDateTime.now());

        RechargeRecordEntity result = rechargeRecordRepository.save(rechargeRecordEntity);
        userServiceImp.updateUser(userId, user);
        return IRechargeRecordMapper.INSTANCE.entityToDto(result);
    }

    @Override
    public List<RechargeRecordDTO> getAllRechargeRecordByUserId(UUID userId) {
        return getAllRechargeRecords().stream()
                .filter(rechargeRecordDTO -> rechargeRecordDTO.userId().equals(userId)).toList();
    }

    @Override
    public List<RechargeRecordDTO> getAllRechargeRecords() {
        return rechargeRecordRepository.findAll().stream()
                .map(IRechargeRecordMapper.INSTANCE::entityToDto)
                .toList();
    }
}
