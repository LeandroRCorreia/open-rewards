package com.rewards.openrewards.modules.wallet.presentation.mapper;

import com.rewards.openrewards.modules.wallet.business.dto.DepositInput;
import com.rewards.openrewards.modules.wallet.business.dto.CreateWalletInput;
import com.rewards.openrewards.modules.wallet.business.dto.WithdrawInput;
import com.rewards.openrewards.modules.wallet.presentation.dto.DepositRequest;
import com.rewards.openrewards.modules.wallet.presentation.dto.UserCreatedEvent;
import com.rewards.openrewards.modules.wallet.presentation.dto.WithdrawRequest;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public CreateWalletInput UserCreatedEventToWalletCreatedInput(UserCreatedEvent userCreatedEvent){
        return CreateWalletInput.builder()
                .email(userCreatedEvent.email())
                .userId(userCreatedEvent.userId())
                .password(userCreatedEvent.password())
                .build();
    }

    public DepositInput AddBalanceToDomain(DepositRequest depositRequest){
        return DepositInput.builder()
                .walletId(depositRequest.walletId())
                .description(depositRequest.description())
                .amount(depositRequest.amount())
                .idempotencyKey(depositRequest.idempotencyKey())
                .build();
    }

    public WithdrawInput withdrawRequestToWithdrawInput(WithdrawRequest withdrawRequest){
        return WithdrawInput.builder()
                .walletId(withdrawRequest.walletId())
                .amount(withdrawRequest.amount())
                .description(withdrawRequest.description())
                .idempotencyKey(withdrawRequest.idempotencyKey())
                .build();
    }


}
