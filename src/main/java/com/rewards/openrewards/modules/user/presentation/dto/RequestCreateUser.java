package com.rewards.openrewards.modules.user.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class RequestCreateUser {
    private String name;
    private String email;
    private String password;
}
