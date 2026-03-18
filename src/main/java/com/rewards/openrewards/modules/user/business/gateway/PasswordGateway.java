package com.rewards.openrewards.modules.user.business.gateway;

public interface PasswordGateway {
    String hash(String rawPassword);
}