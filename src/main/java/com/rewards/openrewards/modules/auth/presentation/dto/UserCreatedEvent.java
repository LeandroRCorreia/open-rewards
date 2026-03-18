package com.rewards.openrewards.modules.auth.presentation.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record UserCreatedEvent(Long userId, String email, String password) {
}
