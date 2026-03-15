package com.rewards.openrewards.modules.wallet.business.gateway;

import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TransactionGateway {
    Page<Transaction> findAllStatement(Long walletId, Pageable pageable);
    Transaction save(Transaction transaction);
}
