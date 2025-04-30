package com.lam.sb_backend.repository;

import com.lam.sb_backend.domain.entity.RechargeRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRechargeRecordRepository extends JpaRepository<RechargeRecordEntity, UUID> {
}
