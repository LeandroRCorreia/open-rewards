package com.rewards.openrewards.modules.wallet.integration.repository;

import com.rewards.openrewards.modules.wallet.integration.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
}
