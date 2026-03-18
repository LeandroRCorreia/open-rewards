package com.rewards.openrewards.modules.user.business.usecase;


import com.rewards.openrewards.modules.user.business.domain.User;
import com.rewards.openrewards.modules.user.business.dto.UserInput;
import com.rewards.openrewards.modules.user.business.event.UserCreatedEvent;
import com.rewards.openrewards.modules.user.business.gateway.EventPublisherGateway;
import com.rewards.openrewards.modules.user.business.gateway.PasswordGateway;
import com.rewards.openrewards.modules.user.business.gateway.UserGateway;
import com.rewards.openrewards.shared.BusinessException;
import com.rewards.openrewards.shared.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements UseCase<UserInput, User> {

    private final UserGateway userGateway;
    private final EventPublisherGateway eventPublisherGateway;
    private final PasswordGateway passwordGateway;

    @Override
    @Transactional
    public User execute(UserInput input) {
        if(userGateway.existsByEmail(input.email())) {
            throw new BusinessException("USER_EMAIL_ALREADY_EXISTS", "Email already exists", HttpStatus.CONFLICT);
        }
        User user = User.buildCreatedUser(input);
        User userCreated = userGateway.create(user);

        String hashedPassword = passwordGateway.hash(input.password());


        UserCreatedEvent userCreatedEvent = User.buildUserCreatedEvent(userCreated, hashedPassword);
        eventPublisherGateway.publish(userCreatedEvent);

        return userCreated;
    }

}
