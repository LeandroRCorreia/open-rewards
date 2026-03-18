package com.rewards.openrewards.modules.auth.business.gateway;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenGateway {

    String generateToken(UserDetails userDetails);


}
