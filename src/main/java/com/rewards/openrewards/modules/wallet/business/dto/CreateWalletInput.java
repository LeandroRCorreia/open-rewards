package com.rewards.openrewards.modules.wallet.business.dto;


import lombok.Builder;

@Builder(toBuilder = true)
public record CreateWalletInput(Long userId, String email, String password) {
}
