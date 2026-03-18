package com.rewards.openrewards.modules.auth.business.gateway;


import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;

public interface AuthGateway {

    AuthCredentials create(AuthCredentials authCredentials);

}
