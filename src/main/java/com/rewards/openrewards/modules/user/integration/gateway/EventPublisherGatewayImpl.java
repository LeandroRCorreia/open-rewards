package com.rewards.openrewards.modules.user.integration.gateway;

import com.rewards.openrewards.modules.user.business.event.UserCreatedEvent;
import com.rewards.openrewards.modules.user.business.gateway.EventPublisherGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EventPublisherGatewayImpl implements EventPublisherGateway {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_WALLET = "user.events.exchange";
    private static final String ROUTING_KEY_WALLET = "user.created";

    @Override
    public void publish(UserCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                EXCHANGE_WALLET,
                ROUTING_KEY_WALLET,
                new UserCreatedEvent(event.id())
        );
    }

}
