package com.rewards.openrewards.modules.auth.presentation.listener;

import com.rewards.openrewards.modules.auth.business.usecase.CreateAuthCredentialsUseCase;
import com.rewards.openrewards.modules.auth.presentation.dto.UserCreatedEvent;
import com.rewards.openrewards.modules.auth.presentation.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAuthCredentialsOnWalletCreatedListener {


    private final AuthMapper authMapper;
    private final CreateAuthCredentialsUseCase createAuthCredentialsUseCase;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "auth_credentials.wallet.created.queue", durable = "true"),
            exchange = @Exchange(value = "wallet.events.exchange", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "wallet.created"
    ))
    public void handle(UserCreatedEvent event) {
        createAuthCredentialsUseCase.execute(authMapper.userCreatedEventToAuthCredentials(event));
        //TODO: ACK
    }


}
