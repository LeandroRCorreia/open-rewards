package com.rewards.openrewards.modules.user.business.gateway;

import com.rewards.openrewards.modules.user.business.domain.User;

public interface UserGateway {

    User create(User user);
    Boolean existsByEmail(String email);

}
