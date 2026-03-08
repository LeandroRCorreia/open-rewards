package com.rewards.openrewards.modules.wallet.presentation.listener;
import com.rewards.openrewards.modules.wallet.presentation.dto.UserCreatedEvent;
import com.rewards.openrewards.modules.wallet.business.usecase.CreateWalletUseCase;
import com.rewards.openrewards.modules.wallet.presentation.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateWalletOnUserCreatedListener {

    private final CreateWalletUseCase createWalletUseCase;
    private final WalletMapper walletMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "wallet.user.created.queue", durable = "true"),
            exchange = @Exchange(value = "user.events.exchange", type = "direct"),
            key = "user.created"
    ))
    public void handle(UserCreatedEvent event) {
        createWalletUseCase.execute(walletMapper.UserCreatedEventToDomain(event));
    }

}