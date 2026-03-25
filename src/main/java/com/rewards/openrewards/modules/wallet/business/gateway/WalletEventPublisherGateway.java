package com.rewards.openrewards.modules.wallet.business.gateway;

import com.rewards.openrewards.modules.wallet.business.event.WalletCreatedEvent;

public interface WalletEventPublisherGateway {

    void publish(WalletCreatedEvent walletCreatedEvent);
}
