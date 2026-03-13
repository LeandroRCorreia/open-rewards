package com.rewards.openrewards.modules.wallet.business.gateway;

import com.rewards.openrewards.modules.wallet.business.domain.Wallet;

import java.util.Optional;

public interface WalletGateway {
    Optional<Wallet> findWalletById(Long walletId);
    Optional<Wallet> findWalletWithLock(Long walletId);
    Wallet create(Wallet wallet);
    Wallet update(Wallet wallet);
}
