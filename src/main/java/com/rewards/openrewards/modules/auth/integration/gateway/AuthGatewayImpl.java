package com.rewards.openrewards.modules.auth.integration.gateway;

import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import com.rewards.openrewards.modules.auth.business.gateway.AuthGateway;
import com.rewards.openrewards.modules.auth.integration.mapper.AuthGatewayEntityMapper;
import com.rewards.openrewards.modules.auth.integration.repository.AuthCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class AuthGatewayImpl implements AuthGateway {

    private final AuthCredentialsRepository authCredentialsRepository;
    private final AuthGatewayEntityMapper authGatewayEntityMapper;

    @Override
    public AuthCredentials create(AuthCredentials authCredentials) {
        return Optional.of(authCredentials)
                .map(authGatewayEntityMapper::toEntity)
                .map(authCredentialsRepository::save)
                .map(authGatewayEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Error to create AuthGateway User"));
    }

    @Override
    public UserDetails findByEmail(String email) {
        return authCredentialsRepository.findByEmail(email)
                .map(authGatewayEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Error to find UserDetails" + email));
    }
}
