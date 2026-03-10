package com.rewards.openrewards.modules.wallet.presentation.controller;


import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.usecase.DepositUseCase;
import com.rewards.openrewards.modules.wallet.presentation.dto.DepositRequest;
import com.rewards.openrewards.modules.wallet.presentation.mapper.WalletMapper;
import com.rewards.openrewards.shared.ApiDefaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletMapper walletMapper;
    private final DepositUseCase depositUseCase;

    @PostMapping("/deposit")
    public ResponseEntity<ApiDefaultResponse<Wallet>> deposit(@RequestBody DepositRequest depositRequest) {
        return Optional.of(depositRequest)
                .map(walletMapper::AddBalanceToDomain)
                .map(depositUseCase::execute)
                .map(wallet -> ResponseEntity.status(HttpStatus.CREATED).body(ApiDefaultResponse.success(wallet)))
                .orElseThrow(() -> new RuntimeException("Unexpected error in deposit"));
    }

}
