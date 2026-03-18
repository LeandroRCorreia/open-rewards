package com.rewards.openrewards.modules.auth.business.dto;


import lombok.Builder;

@Builder(toBuilder = true)
public record LoginOutput (String token){

}

