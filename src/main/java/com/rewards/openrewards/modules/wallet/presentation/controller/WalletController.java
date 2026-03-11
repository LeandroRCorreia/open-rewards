package com.rewards.openrewards.modules.wallet.presentation.controller;


import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.usecase.DepositUseCase;
import com.rewards.openrewards.modules.wallet.business.usecase.WithdrawUseCase;
import com.rewards.openrewards.modules.wallet.presentation.dto.DepositRequest;
import com.rewards.openrewards.modules.wallet.presentation.dto.WithdrawRequest;
import com.rewards.openrewards.modules.wallet.presentation.mapper.WalletMapper;
import com.rewards.openrewards.shared.ApiDefaultResponse;
import feign.Response;
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
    private final WithdrawUseCase withdrawUseCase;

    @PostMapping("/deposit")
    public ResponseEntity<ApiDefaultResponse<Wallet>> deposit(@RequestBody DepositRequest depositRequest) {
        return Optional.of(depositRequest)
                .map(walletMapper::AddBalanceToDomain)
                .map(depositUseCase::execute)
                .map(wallet -> ResponseEntity.status(HttpStatus.CREATED).body(ApiDefaultResponse.created(wallet)))
                .orElseThrow(() -> new RuntimeException("Unexpected error in deposit"));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiDefaultResponse<Wallet>> withDraw(@RequestBody WithdrawRequest withdrawRequest) {
        return Optional.of(withdrawRequest)
                .map(walletMapper::withdrawRequestToWithdrawInput)
                .map(withdrawUseCase::execute)
                .map(wallet -> ResponseEntity.status(HttpStatus.CREATED).body(ApiDefaultResponse.created(wallet)))
                .orElseThrow(() -> new RuntimeException("Unexpected error in withdraw"));
    }


}
