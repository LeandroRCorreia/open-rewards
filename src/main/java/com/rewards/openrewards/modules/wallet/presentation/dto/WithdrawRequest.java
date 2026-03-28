package com.rewards.openrewards.modules.wallet.presentation.dto;

import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;

@Builder
public record WithdrawRequest(
        @With
        Long walletId,
        BigDecimal amount,
        String description,
        String idempotencyKey
) {
}









