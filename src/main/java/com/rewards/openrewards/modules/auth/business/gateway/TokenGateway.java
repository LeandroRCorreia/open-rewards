package com.rewards.openrewards.modules.auth.business.gateway;

import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;

public interface TokenGateway {

    String generateToken(AuthCredentials userDetails);

    AuthCredentials validateToken(String token);
}
