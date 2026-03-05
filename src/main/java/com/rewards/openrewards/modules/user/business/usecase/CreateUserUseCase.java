package com.rewards.openrewards.modules.user.business.usecase;


import com.rewards.openrewards.modules.user.business.domain.User;
import com.rewards.openrewards.modules.user.business.gateway.UserGateway;
import com.rewards.openrewards.shared.BusinessException;
import com.rewards.openrewards.shared.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements UseCase<User, User> {

    private final UserGateway userGateway;

    @Override
    @Transactional
    public User execute(User input) {
        if(userGateway.existsByEmail(input.getEmail())) {
            throw new BusinessException("USER_EMAIL_ALREADY_EXISTS", "Email already exists", HttpStatus.CONFLICT);
        }
        return userGateway.create(input);
    }
}
