package com.rewards.openrewards.modules.wallet.business.domain;

import com.rewards.openrewards.modules.wallet.business.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class Transaction {
    private Long id;
    private Long idWallet;
    private BigDecimal amount;
    private TransactionType transactionType;
    private String description;
    private LocalDateTime created_at;


    public static Transaction createDeposit(Long walletId, BigDecimal amount, String description) {
        return Transaction.builder()
                .idWallet(walletId)
                .amount(amount)
                .transactionType(TransactionType.DEPOSIT)
                .description(description)
                .build();
    }

    public static Transaction createWithdraw(Long walletId, BigDecimal amount, String description){
        return Transaction.builder()
                .idWallet(walletId)
                .amount(amount)
                .transactionType(TransactionType.CASH_OUT)
                .description(description)
                .build();
    }

}
