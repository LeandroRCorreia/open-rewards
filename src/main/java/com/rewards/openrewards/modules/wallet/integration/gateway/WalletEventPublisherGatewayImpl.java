package com.rewards.openrewards.modules.wallet.integration.gateway;

import com.rewards.openrewards.modules.wallet.business.event.WalletCreatedEvent;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletEventPublisherGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class WalletEventPublisherGatewayImpl implements WalletEventPublisherGateway {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_WALLET = "wallet.events.exchange";
    private static final String ROUTING_KEY_WALLET_CREATED = "wallet.created";

    @Override
    public void publish(WalletCreatedEvent walletCreatedEvent) {
        rabbitTemplate.convertAndSend(
                EXCHANGE_WALLET,
                ROUTING_KEY_WALLET_CREATED,
                walletCreatedEvent
        );
    }
}
