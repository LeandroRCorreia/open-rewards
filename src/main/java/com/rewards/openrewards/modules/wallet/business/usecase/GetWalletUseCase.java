package com.rewards.openrewards.modules.wallet.business.usecase;

import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletGateway;
import com.rewards.openrewards.shared.BusinessException;
import com.rewards.openrewards.shared.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetWalletUseCase implements UseCase<Long, Wallet> {

    private final WalletGateway walletGateway;

    @Override
    @Cacheable(value = "wallet-balance", key = "#walletId")
    public Wallet execute(Long walletId) {
        return walletGateway.findWalletById(walletId)
                .orElseThrow(() -> new BusinessException("Wallet not found", "WALLET_NOT_FOUND", HttpStatus.NOT_FOUND));
    }

}
