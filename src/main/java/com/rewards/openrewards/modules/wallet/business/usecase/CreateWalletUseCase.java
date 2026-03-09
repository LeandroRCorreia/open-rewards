package com.rewards.openrewards.modules.wallet.business.usecase;

import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletGateway;
import com.rewards.openrewards.shared.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class CreateWalletUseCase implements UseCase<Wallet, Wallet> {

    private final WalletGateway walletGateway;

    @Override
    public Wallet execute(Wallet input) {
        Wallet newWallet = buildCreateWallet(input);
        return walletGateway.create(newWallet);
    }

    private Wallet buildCreateWallet(Wallet userCreatedEvent){
        return Wallet.builder()
                .userId(userCreatedEvent.getUserId())
                .balance(BigDecimal.ZERO)
                .build();
    }


}
