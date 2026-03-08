package com.rewards.openrewards.modules.wallet.integration.gateway;

import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletGateway;
import com.rewards.openrewards.modules.wallet.integration.mapper.WalletEntityMapper;
import com.rewards.openrewards.modules.wallet.integration.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class WalletGatewayImpl implements WalletGateway {

    private final WalletRepository walletRepository;

    private final WalletEntityMapper walletEntityMapper;

    @Override
    public Wallet create(Wallet wallet) {
        return Optional.of(wallet)
                .map(walletEntityMapper::toEntity)
                .map(walletRepository::save)
                .map(walletEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Error to create Wallet"));
    }

}
