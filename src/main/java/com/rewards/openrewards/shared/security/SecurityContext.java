package com.rewards.openrewards.shared.security;

public interface SecurityContext {
    Long getCurrentUserId();
    Long getCurrentWalletId();
}
