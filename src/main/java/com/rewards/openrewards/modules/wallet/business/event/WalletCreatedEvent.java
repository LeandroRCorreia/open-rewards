package com.rewards.openrewards.modules.wallet.business.event;

import lombok.Builder;

@Builder(toBuilder = true)
public record WalletCreatedEvent(Long userId, Long walletId, String email, String password) {
}
