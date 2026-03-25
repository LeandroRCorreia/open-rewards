package com.rewards.openrewards.modules.wallet.business.usecase;

import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.dto.CreateWalletInput;
import com.rewards.openrewards.modules.wallet.business.event.WalletCreatedEvent;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletEventPublisherGateway;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletGateway;
import com.rewards.openrewards.shared.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class CreateWalletUseCase implements UseCase<CreateWalletInput, Wallet> {

    private final WalletGateway walletGateway;

    private final WalletEventPublisherGateway walletEventPublisherGateway;

    @Override
    public Wallet execute(CreateWalletInput input) {
        Wallet newWallet = buildWallet(input);

        Wallet walletCreated = walletGateway.create(newWallet);

        WalletCreatedEvent build = buildWalletCreatedEvent(input, walletCreated.getId());

        walletEventPublisherGateway.publish(build);

        return walletCreated;
    }

    private Wallet buildWallet(CreateWalletInput createWalletInput){
        return Wallet.builder()
                .userId(createWalletInput.userId())
                .balance(BigDecimal.ZERO)
                .build();
    }

    private WalletCreatedEvent buildWalletCreatedEvent(CreateWalletInput createWalletInput, Long walletId){
        return WalletCreatedEvent.builder()
                .walletId(walletId)
                .password(createWalletInput.password())
                .email(createWalletInput.email())
                .userId(createWalletInput.userId())
                .build();
    }

}
