package com.rewards.openrewards.modules.wallet.integration.repository;

import com.rewards.openrewards.modules.wallet.integration.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Page<TransactionEntity> findAllByWalletIdOrderByCreatedAtDesc(Long walletId, Pageable pageable);

}
