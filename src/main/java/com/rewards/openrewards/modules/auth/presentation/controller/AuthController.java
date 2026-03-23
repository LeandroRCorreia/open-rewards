package com.rewards.openrewards.modules.auth.presentation.controller;


import com.rewards.openrewards.modules.auth.business.dto.LoginInput;
import com.rewards.openrewards.modules.auth.business.dto.LoginOutput;
import com.rewards.openrewards.modules.auth.business.usecase.LoginUseCase;
import com.rewards.openrewards.modules.auth.presentation.dto.LoginRequest;
import com.rewards.openrewards.modules.auth.presentation.mapper.AuthMapper;
import com.rewards.openrewards.shared.ApiDefaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthMapper authMapper;


    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<ApiDefaultResponse<?>> login(@RequestBody LoginRequest loginRequest){
        LoginInput loginInput =  authMapper.loginRequestToLoginInput(loginRequest);

        LoginOutput loginOutput = loginUseCase.execute(loginInput);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiDefaultResponse.created(loginOutput));
    }

}
