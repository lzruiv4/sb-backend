package com.lam.sb_backend.repository;

import com.lam.sb_backend.domain.entity.PokemonRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPokemonRecordRepository extends JpaRepository<PokemonRecordEntity, UUID> {
}
