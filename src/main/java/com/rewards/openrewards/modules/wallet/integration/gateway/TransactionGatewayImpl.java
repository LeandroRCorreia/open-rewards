package com.rewards.openrewards.modules.wallet.integration.gateway;


import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import com.rewards.openrewards.modules.wallet.business.gateway.TransactionGateway;
import com.rewards.openrewards.modules.wallet.integration.mapper.TransactionEntityMapper;
import com.rewards.openrewards.modules.wallet.integration.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionGatewayImpl implements TransactionGateway {

    private final TransactionRepository transactionRepository;
    private final TransactionEntityMapper transactionEntityMapper;

    @Override
    public Transaction save(Transaction transaction){
        return Optional.of(transaction)
                .map(transactionEntityMapper::toEntity)
                .map(transactionRepository::save)
                .map(transactionEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Error to create transaction"));
    }

}
