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
}
