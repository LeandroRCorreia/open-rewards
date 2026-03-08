package com.rewards.openrewards.modules.wallet.presentation.mapper;

import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.presentation.dto.UserCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {


    public Wallet UserCreatedEventToDomain(UserCreatedEvent userCreatedEvent){
        return Wallet.builder()
                .userId(userCreatedEvent.id())
                .build();
    }

}
