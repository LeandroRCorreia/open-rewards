package com.rewards.openrewards.modules.wallet.business.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WithdrawInput(
        Long walletId,
        BigDecimal amount,
        String description,
        String idempotencyKey
) {
    public boolean isInvalidAmount() {
        return amount == null || amount.compareTo(BigDecimal.ZERO) <= 0;
    }

}
