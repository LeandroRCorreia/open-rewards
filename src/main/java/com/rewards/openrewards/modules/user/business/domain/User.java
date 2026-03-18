package com.rewards.openrewards.modules.user.business.domain;

import com.rewards.openrewards.modules.user.business.dto.UserInput;
import com.rewards.openrewards.modules.user.business.event.UserCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class User {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static User buildCreatedUser(UserInput userInput){
        return User.builder()
                .name(userInput.name())
                .email(userInput.email())
                .build();
    }

    public static UserCreatedEvent buildUserCreatedEvent(User user, String hashedPassword) {
        return UserCreatedEvent.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .password(hashedPassword)
                .build();
    }

}
