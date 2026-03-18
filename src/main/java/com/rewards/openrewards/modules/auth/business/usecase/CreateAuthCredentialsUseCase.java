package com.rewards.openrewards.modules.auth.business.usecase;

import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import com.rewards.openrewards.modules.auth.business.gateway.AuthGateway;
import com.rewards.openrewards.shared.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAuthCredentialsUseCase implements UseCase<AuthCredentials, AuthCredentials> {

    private final AuthGateway authGateway;

    @Override
    public AuthCredentials execute(AuthCredentials input) {
        return authGateway.create(input);
    }
}
