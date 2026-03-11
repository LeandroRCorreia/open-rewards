package com.rewards.openrewards.modules.wallet.integration.mapper;

import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import com.rewards.openrewards.modules.wallet.business.enums.TransactionType;
import com.rewards.openrewards.modules.wallet.integration.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionEntityMapper {

    public Transaction toDomain(TransactionEntity transactionEntity){
        return Transaction.builder()
                .id(transactionEntity.getId())
                .idWallet(transactionEntity.getWalletId())
                .amount(transactionEntity.getAmount())
                .transactionType(TransactionType.valueOf(transactionEntity.getType()))
                .description(transactionEntity.getDescription())
                .idempotencyKey(transactionEntity.getIdempotencyKey().toString())
                .created_at(transactionEntity.getCreatedAt())
                .build();
    }

    public TransactionEntity toEntity(Transaction transaction){
        return TransactionEntity.builder()
                .id(transaction.getId())
                .walletId(transaction.getIdWallet())
                .amount(transaction.getAmount())
                .type(transaction.getTransactionType().name())
                .description(transaction.getDescription())
                .idempotencyKey(UUID.fromString(transaction.getIdempotencyKey()))
                .createdAt(transaction.getCreated_at())
                .build();
    }

}
