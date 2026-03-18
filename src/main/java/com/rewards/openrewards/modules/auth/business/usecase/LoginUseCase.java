package com.rewards.openrewards.modules.auth.business.usecase;


import com.rewards.openrewards.modules.auth.business.dto.LoginInput;
import com.rewards.openrewards.modules.auth.business.dto.LoginOutput;
import com.rewards.openrewards.modules.auth.business.gateway.TokenGateway;
import com.rewards.openrewards.shared.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements UseCase<LoginInput, LoginOutput> {

    private final AuthenticationManager authenticationManager;
    private final TokenGateway tokenGateway;

    @Override
    public LoginOutput execute(LoginInput input) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(input.email(),
                input.password());
        Authentication authenticate = authenticationManager.authenticate(authToken);

        UserDetails principal = (UserDetails) authenticate.getPrincipal();

        String token = tokenGateway.generateToken(principal);

        return LoginOutput.builder().token(token).build();
    }

}
