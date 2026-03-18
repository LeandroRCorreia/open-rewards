package com.rewards.openrewards.modules.user.business.dto;


import lombok.Builder;

@Builder(toBuilder = true)
public record UserInput(String name, String email, String password) {
}
