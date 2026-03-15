package com.rewards.openrewards.modules.wallet.presentation.mapper;


import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import com.rewards.openrewards.modules.wallet.presentation.dto.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponse domainToResponse(Transaction transaction){
        return TransactionResponse.builder()
                .id(transaction.getId())
                .type(transaction.getTransactionType().getDescription())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

}
