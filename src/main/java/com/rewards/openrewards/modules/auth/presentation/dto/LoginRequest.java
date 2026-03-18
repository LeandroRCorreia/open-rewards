package com.rewards.openrewards.modules.auth.presentation.dto;


import lombok.Builder;

@Builder(toBuilder = true)
public record LoginRequest(String email, String password) {
}
