package com.rewards.openrewards.modules.wallet.business.gateway;

import com.rewards.openrewards.modules.wallet.business.domain.Transaction;


public interface TransactionGateway {
    Transaction save(Transaction transaction);
}
