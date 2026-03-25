package com.rewards.openrewards.shared.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityContext implements SecurityContext {

    @Override
    public Long getCurrentUserId() {
        return getPrincipal().getAuthenticatedUser().userId();
    }

    @Override
    public Long getCurrentWalletId() {
        return getPrincipal().getAuthenticatedUser().walletId();
    }

    private AuthenticatedPrincipal getPrincipal() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        return (AuthenticatedPrincipal) auth.getPrincipal();
    }
}