package com.rewards.openrewards.modules.wallet.presentation.dto;

import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record DepositRequest(
        Long walletId,
        BigDecimal amount,
        String description,
        String idempotencyKey
) {
}
