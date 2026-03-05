package com.rewards.openrewards.modules.user.integration.gateway;

import com.rewards.openrewards.modules.user.business.domain.User;
import com.rewards.openrewards.modules.user.business.gateway.UserGateway;
import com.rewards.openrewards.modules.user.integration.mapper.UserEntityMapper;
import com.rewards.openrewards.modules.user.integration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userMapper;

    @Override
    public User create(User user) {
        return Optional.of(user)
                .map(userMapper::userToEntity)
                .map(userRepository::save)
                .map(userMapper::entityToUser)
                .orElseThrow(() -> new RuntimeException("Error to create User"));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
