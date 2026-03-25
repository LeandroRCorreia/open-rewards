package com.rewards.openrewards.modules.wallet.presentation.dto;


import lombok.Builder;

@Builder(toBuilder = true)
public record UserCreatedEvent(Long userId, Long walletId, String email, String password) {
}