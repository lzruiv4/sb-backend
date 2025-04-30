package com.lam.sb_backend.serviceImp;

import com.lam.sb_backend.domain.dto.RechargeRecordDTO;
import com.lam.sb_backend.domain.entity.RechargeRecordEntity;
import com.lam.sb_backend.domain.entity.UserEntity;
import com.lam.sb_backend.mapper.IRechargeRecordMapper;
import com.lam.sb_backend.repository.IRechargeRecordRepository;
import com.lam.sb_backend.repository.IUserRepository;
import com.lam.sb_backend.service.IRechargeRecordService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RechargeRecordServiceImp implements IRechargeRecordService {

    private final IRechargeRecordRepository rechargeRecordRepository;

    private final IUserRepository userRepository;

    @Override
    public RechargeRecordDTO addRechargeRecord(UUID userId, int amountRecharge) {
        if(amountRecharge <= 0) {
            throw new RuntimeException("Amount recharge must be greater than 0");
        }

        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        RechargeRecordEntity rechargeRecordEntity = new RechargeRecordEntity();
        rechargeRecordEntity.setAmountRecharge(amountRecharge);
        int currentPokemonCoin = userEntity.getPokemonCoin() + amountRecharge;
        userEntity.setPokemonCoin(currentPokemonCoin);
        rechargeRecordEntity.setCurrentPokemonCoin(currentPokemonCoin);
        rechargeRecordEntity.setUserEntity(userEntity);
        rechargeRecordEntity.setRechargeAt(LocalDateTime.now());

        RechargeRecordEntity result = rechargeRecordRepository.save(rechargeRecordEntity);
        userRepository.save(userEntity);
        return IRechargeRecordMapper.INSTANCE.entityToDto(result);
    }

    @Override
    public List<RechargeRecordDTO> getAllRechargeRecordByUserId(UUID userId) {
        return rechargeRecordRepository.findAll().stream()
                .filter(rechargeRecordEntity -> rechargeRecordEntity.getUserEntity().getUserId().equals(userId))
                .map(IRechargeRecordMapper.INSTANCE::entityToDto)
                .toList();
    }
}
