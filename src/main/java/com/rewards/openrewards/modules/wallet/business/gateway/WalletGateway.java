package com.rewards.openrewards.modules.wallet.business.gateway;

import com.rewards.openrewards.modules.wallet.business.domain.Wallet;

public interface WalletGateway {

    Wallet create(Wallet wallet);
}
