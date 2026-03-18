package com.rewards.openrewards.modules.user.presentation.mapper;

import com.rewards.openrewards.modules.user.business.domain.User;
import com.rewards.openrewards.modules.user.business.dto.UserInput;
import com.rewards.openrewards.modules.user.presentation.dto.RequestCreateUser;
import com.rewards.openrewards.modules.user.presentation.dto.ResponseUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public ResponseUser UserToResponseUser(User user){
        return ResponseUser.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }


    public UserInput requestCreateUserToUserInput(RequestCreateUser requestCreateUser){
        return UserInput.builder()
                .name(requestCreateUser.getName())
                .email(requestCreateUser.getEmail())
                .password(requestCreateUser.getPassword())
                .build();
    }

    public User requestCreateUserToUser(final RequestCreateUser requestCreateUser){
        return User.builder()
                .name(requestCreateUser.getName())
                .email(requestCreateUser.getEmail())
                .build();
    }

}
