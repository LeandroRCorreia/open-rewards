package com.rewards.openrewards.modules.wallet.presentation.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionResponse(
        Long id,
        BigDecimal amount,
        String description,
        String type,
        LocalDateTime createdAt
) {
}
