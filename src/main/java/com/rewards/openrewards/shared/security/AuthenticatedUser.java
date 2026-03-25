package com.rewards.openrewards.shared.security;

import lombok.Builder;

@Builder
public record AuthenticatedUser(
        Long id,
        Long userId,
        Long walletId,
        String email,
        String role
) {}
