package com.rewards.openrewards.modules.user.presentation.controller;

import com.rewards.openrewards.modules.user.presentation.dto.RequestCreateUser;
import com.rewards.openrewards.modules.user.presentation.mapper.UserMapper;
import com.rewards.openrewards.modules.user.business.usecase.CreateUserUseCase;
import com.rewards.openrewards.modules.user.presentation.dto.ResponseUser;
import com.rewards.openrewards.shared.ApiDefaultResponse;
import com.rewards.openrewards.shared.DataWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<ApiDefaultResponse<ResponseUser>> create(
            @RequestBody @Valid RequestCreateUser request,
            UriComponentsBuilder uriBuilder
    ) {
        return Optional.of(request)
                .map(userMapper::requestCreateUserToUserInput)
                .map(createUserUseCase::execute)
                .map(userMapper::UserToResponseUser)
                .map(user -> {
                    URI uri = uriBuilder.path("/v1/users/{publicId}")
                            .buildAndExpand(user.getId())
                            .toUri();
                    return ResponseEntity
                            .created(uri)
                            .body(ApiDefaultResponse.created(user));
                })
                .orElseThrow(() -> new RuntimeException("Erro inesperado no processamento"));
    }

}
