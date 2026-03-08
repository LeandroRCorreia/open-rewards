package com.rewards.openrewards.modules.wallet.business.usecase;
import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletGateway;
import com.rewards.openrewards.modules.wallet.business.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateWalletOnUserCreatedListener {

    private final WalletGateway walletGateway;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "wallet.user.created.queue", durable = "true"),
            exchange = @Exchange(value = "user.events.exchange", type = "direct"),
            key = "user.created"
    ))
    public void handle(UserCreatedEvent event) {
        Wallet newWallet = buildCreateWallet(event);

        walletGateway.create(newWallet);
    }

    private Wallet buildCreateWallet(UserCreatedEvent userCreatedEvent){
        return Wallet.builder()
                .userId(userCreatedEvent.id())
                .balance(BigDecimal.ZERO)
                .build();
    }

}