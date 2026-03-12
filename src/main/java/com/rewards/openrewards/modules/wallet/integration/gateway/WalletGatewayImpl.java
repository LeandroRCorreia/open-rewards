package com.rewards.openrewards.modules.wallet.integration.gateway;

import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletGateway;
import com.rewards.openrewards.modules.wallet.integration.entity.WalletEntity;
import com.rewards.openrewards.modules.wallet.integration.mapper.WalletEntityMapper;
import com.rewards.openrewards.modules.wallet.integration.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class WalletGatewayImpl implements WalletGateway {

    private final WalletRepository walletRepository;

    private final WalletEntityMapper walletEntityMapper;

    public Optional<Wallet> findWalletWithLock(Long id){
        return walletRepository.findByIdWithLock(id)
                .map(walletEntityMapper::toDomain);
    }

    @Override
    public Wallet create(Wallet wallet) {
        return Optional.of(wallet)
                .map(walletEntityMapper::toEntity)
                .map(walletRepository::save)
                .map(walletEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Error to create Wallet"));
    }

    @Override
    public Wallet update(Wallet wallet) {
        return walletRepository.findById(wallet.getId())
                .map(existing -> walletEntityMapper.toEntity(wallet))
                .map(walletRepository::save)
                .map(walletEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Wallet not found for update"));
    }

}
