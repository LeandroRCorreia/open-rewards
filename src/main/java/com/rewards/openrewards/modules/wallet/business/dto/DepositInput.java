package com.rewards.openrewards.modules.wallet.business.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record DepositInput(
        Long walletId,
        BigDecimal amount,
        String description,
        String idempotencyKey
) {

    public Boolean isDepositZero(){
        return this.amount.equals(BigDecimal.ZERO);
    }

}