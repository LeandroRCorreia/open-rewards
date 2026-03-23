package com.rewards.openrewards.modules.auth.business.usecase;


import com.rewards.openrewards.modules.auth.business.gateway.AuthGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationUseCase implements UserDetailsService {


    private final AuthGateway authGateway;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authGateway.findByEmail(username);
    }
}
