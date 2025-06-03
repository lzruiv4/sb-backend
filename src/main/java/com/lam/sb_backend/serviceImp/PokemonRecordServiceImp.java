package com.lam.sb_backend.serviceImp;

import com.lam.sb_backend.domain.dto.PokemonRecordDTO;
import com.lam.sb_backend.domain.dto.UserDTO;
import com.lam.sb_backend.domain.entity.PokemonRecordEntity;
import com.lam.sb_backend.domain.model.PokemonRecord;
import com.lam.sb_backend.domain.model.User;
import com.lam.sb_backend.exception.PokemonRecordNotFoundException;
import com.lam.sb_backend.mapper.IPokemonRecordMapper;
import com.lam.sb_backend.mapper.IUserMapper;
import com.lam.sb_backend.repository.IPokemonRecordRepository;
import com.lam.sb_backend.service.IPokemonRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PokemonRecordServiceImp implements IPokemonRecordService {

    private final IPokemonRecordRepository pokemonRecordRepository;

    private final UserServiceImp userServiceImp;

    @Override
    public PokemonRecordDTO createPokemonRecord(String pokemonId, UUID userId) {
        UserDTO currentUserDTO = userServiceImp.getUserById(userId);
        User currentUser = IUserMapper.INSTANCE.dtoToModel(currentUserDTO);
        int currentCoin = currentUser.getPokemonCoin();
        currentUser.setPokemonCoin(currentCoin - 1);

        PokemonRecord pokemonRecord = new PokemonRecord();
        pokemonRecord.setPokemonId(pokemonId);
        pokemonRecord.setCaptureTime(LocalDateTime.now());
        pokemonRecord.setUser(currentUser);
        pokemonRecord.setRelease(false);

        PokemonRecordEntity result = pokemonRecordRepository.save(
                IPokemonRecordMapper.INSTANCE.modelToEntity(pokemonRecord));
        userServiceImp.updateUser(userId, currentUser);
        return IPokemonRecordMapper.INSTANCE.entityToDto(result);
    }

    @Override
    public PokemonRecordDTO changeReleaseToTrue(UUID pokemonRecordId) {
        PokemonRecordEntity pokemonRecordEntity = pokemonRecordRepository.findById(pokemonRecordId)
                .orElseThrow(() -> new PokemonRecordNotFoundException(
                        pokemonRecordId,
                        new Throwable("changeReleaseToTrue")));
        pokemonRecordEntity.setRelease(true);
        return IPokemonRecordMapper.INSTANCE.entityToDto(pokemonRecordRepository.save(pokemonRecordEntity));
    }

    @Override
    public PokemonRecordDTO updatePokemonRecord(UUID pokemonRecordId, PokemonRecord pokemonRecord) {
        PokemonRecordEntity pokemonRecordEntity = pokemonRecordRepository.findById(pokemonRecordId)
                .orElseThrow(() -> new PokemonRecordNotFoundException(
                        pokemonRecordId,
                        new Throwable("updatePokemonRecord"))
                );
        // TODO: check rechargeRecordDTO valid or check the change
        // UserEntity can not be changed here
        pokemonRecordEntity.setPokemonId(pokemonRecord.getPokemonId());
        pokemonRecordEntity.setCaptureTime(pokemonRecord.getCaptureTime());
        return IPokemonRecordMapper.INSTANCE.entityToDto(pokemonRecordRepository.save(pokemonRecordEntity));
    }

    @Override
    public List<PokemonRecordDTO> getPokemonRecordsByUserId(UUID userId) {
        return getAllPokemonRecords().stream()
                .filter(pokemonRecordDTO -> pokemonRecordDTO.userId().equals(userId))
                .toList();
    }

    @Override
    public List<PokemonRecordDTO> getAllPokemonRecords() {
        return pokemonRecordRepository.findAll().stream()
                .sorted(Comparator.comparing(PokemonRecordEntity::getCaptureTime).reversed())
                .map(IPokemonRecordMapper.INSTANCE::entityToDto)
                .toList();
    }
}
