package com.rewards.openrewards.modules.user.business.gateway;

import com.rewards.openrewards.modules.user.business.event.UserCreatedEvent;

public interface EventPublisherGateway {
    void publish(UserCreatedEvent event);
}
