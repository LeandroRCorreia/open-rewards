package com.rewards.openrewards.modules.wallet.business.dto;


import lombok.Builder;
import org.springframework.data.domain.Pageable;


@Builder(toBuilder = true)
public record GetTransactionInput(Long walletId, Pageable pageable) {
}
