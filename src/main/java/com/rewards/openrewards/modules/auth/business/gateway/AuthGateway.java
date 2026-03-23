package com.rewards.openrewards.modules.auth.business.gateway;


import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthGateway {

    AuthCredentials create(AuthCredentials authCredentials);
    UserDetails findByEmail(String email);

}
