package com.rewards.openrewards.modules.auth.business.usecase;

import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import com.rewards.openrewards.modules.auth.business.gateway.AuthGateway;
import com.rewards.openrewards.modules.auth.business.gateway.TokenGateway;
import com.rewards.openrewards.modules.auth.business.dto.LoginInput;
import com.rewards.openrewards.modules.auth.business.dto.LoginOutput;
import com.rewards.openrewards.shared.BusinessException;
import com.rewards.openrewards.shared.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements UseCase<LoginInput, LoginOutput> {

    private final AuthGateway authGateway;
    private final TokenGateway tokenGateway;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginOutput execute(LoginInput input) {
        AuthCredentials credentials = authGateway.findByEmail(input.email());
        if (!passwordEncoder.matches(input.password(), credentials.getPasswordHash())) {
            throw new BusinessException(
                    "INVALID_CREDENTIALS",
                    "Credenciais inválidas",
                    HttpStatus.UNAUTHORIZED
            );
        }

        String token = tokenGateway.generateToken(credentials);
        return LoginOutput.builder().token(token).build();
    }
}