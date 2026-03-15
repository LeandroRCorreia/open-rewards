package com.rewards.openrewards.modules.wallet.integration.gateway;


import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import com.rewards.openrewards.modules.wallet.business.gateway.TransactionGateway;
import com.rewards.openrewards.modules.wallet.integration.mapper.TransactionEntityMapper;
import com.rewards.openrewards.modules.wallet.integration.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionGatewayImpl implements TransactionGateway {

    private final TransactionRepository transactionRepository;
    private final TransactionEntityMapper transactionEntityMapper;

    @Override
    public Page<Transaction> findAllStatement(Long walletId, Pageable pageable){
        return transactionRepository.findAllByWalletIdOrderByCreatedAtDesc(walletId, pageable)
                .map(transactionEntityMapper::toDomain);
    }

    @Override
    public Transaction save(Transaction transaction){
        return Optional.of(transaction)
                .map(transactionEntityMapper::toEntity)
                .map(transactionRepository::save)
                .map(transactionEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Error to create transaction"));
    }

}
