package com.rewards.openrewards.modules.auth.presentation.mapper;


import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import com.rewards.openrewards.modules.auth.business.dto.LoginInput;
import com.rewards.openrewards.modules.auth.presentation.dto.LoginRequest;
import com.rewards.openrewards.modules.auth.presentation.dto.UserCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {



    public LoginInput loginRequestToLoginInput(LoginRequest loginRequest){
        return LoginInput.builder()
                .email(loginRequest.email())
                .password(loginRequest.password())
                .build();
    }

    public AuthCredentials userCreatedEventToAuthCredentials(UserCreatedEvent userCreatedEvent){
        return AuthCredentials.builder()
                .userId(userCreatedEvent.userId())
                .email(userCreatedEvent.email())
                .passwordHash(userCreatedEvent.password())
                .build();
    }

}
