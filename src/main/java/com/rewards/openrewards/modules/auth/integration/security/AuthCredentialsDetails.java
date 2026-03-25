package com.rewards.openrewards.modules.auth.integration.security;


import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import com.rewards.openrewards.modules.auth.business.enums.UserRoles;
import com.rewards.openrewards.shared.security.AuthenticatedPrincipal;
import com.rewards.openrewards.shared.security.AuthenticatedUser;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder(toBuilder = true)
public record AuthCredentialsDetails(AuthCredentials credentials) implements UserDetails, AuthenticatedPrincipal {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.credentials.getRoles() == UserRoles.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return credentials.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return credentials.getEmail();
    }

    @Override
    public AuthenticatedUser getAuthenticatedUser() {
        return AuthenticatedUser.builder()
                .id(credentials.getId())
                .userId(credentials.getUserId())
                .walletId(credentials.getWalletId())
                .email(credentials.getEmail())
                .role(credentials.getRoles().name())
                .build();
    }


}
