package com.rewards.openrewards.modules.wallet.business.domain;

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
public class Wallet {
    private Long id;
    private BigDecimal balance;
    private Long userId;
    private LocalDateTime updatedAt;

    public Wallet deposit(BigDecimal amount){
        return this.toBuilder()
                .balance(this.getBalance().add(amount))
                .build();
    }

    public Boolean isSufficientBalance(BigDecimal amount){
        return this.balance.compareTo(amount) >= 0.0;
    }

    public Boolean isNotSufficientBalance(BigDecimal amount){
        return !isSufficientBalance(amount);
    }

    public Wallet withdraw(BigDecimal amount){
        return this.toBuilder()
                .balance(this.getBalance().subtract(amount))
                .build();
    }

}
