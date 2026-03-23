package com.rewards.openrewards.modules.auth.business.gateway;

import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenGateway {

    String generateToken(UserDetails userDetails);

    AuthCredentials validateToken(String token);
}
