package com.rewards.openrewards.modules.wallet.presentation.controller;


import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.dto.GetTransactionInput;
import com.rewards.openrewards.modules.wallet.business.usecase.DepositUseCase;
import com.rewards.openrewards.modules.wallet.business.usecase.GetWalletUseCase;
import com.rewards.openrewards.modules.wallet.business.usecase.StatementUseCase;
import com.rewards.openrewards.modules.wallet.business.usecase.WithdrawUseCase;
import com.rewards.openrewards.modules.wallet.presentation.dto.DepositRequest;
import com.rewards.openrewards.modules.wallet.presentation.dto.TransactionResponse;
import com.rewards.openrewards.modules.wallet.presentation.dto.WithdrawRequest;
import com.rewards.openrewards.modules.wallet.presentation.mapper.TransactionMapper;
import com.rewards.openrewards.modules.wallet.presentation.mapper.WalletMapper;
import com.rewards.openrewards.shared.ApiDefaultResponse;
import com.rewards.openrewards.shared.security.SpringSecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletMapper walletMapper;
    private final TransactionMapper transactionMapper;

    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;
    private final GetWalletUseCase getWalletUseCase;
    private final StatementUseCase statementUseCase;

    private final SpringSecurityContext springSecurityContext;

    @GetMapping
    public ResponseEntity<ApiDefaultResponse<Wallet>> getWallet() {
            return Optional.of(springSecurityContext.getCurrentWalletId())
                .map(getWalletUseCase::execute)
                .map(wallet -> ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiDefaultResponse.success(wallet)))
                .orElseThrow(() -> new RuntimeException("Unexpected error in deposit"));
    }

    @GetMapping("/statement")
    public ResponseEntity<ApiDefaultResponse<Page<TransactionResponse>>> getWalletPageable(
            Pageable pageable) {
        Page<Transaction> transactions = statementUseCase.execute(
                GetTransactionInput.builder()
                        .walletId(springSecurityContext.getCurrentWalletId())
                        .pageable(pageable)
                        .build());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiDefaultResponse.success(transactions.map(transactionMapper::domainToResponse)));
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiDefaultResponse<Wallet>> deposit(@RequestBody DepositRequest depositRequest) {
        return Optional.of(springSecurityContext.getCurrentWalletId())
                .map(depositRequest::withWalletId)
                .map(walletMapper::AddBalanceToDomain)
                .map(depositUseCase::execute)
                .map(wallet -> ResponseEntity.status(HttpStatus.CREATED).body(ApiDefaultResponse.created(wallet)))
                .orElseThrow(() -> new RuntimeException("Unexpected error in deposit"));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiDefaultResponse<Wallet>> withDraw(@RequestBody WithdrawRequest withdrawRequest) {
        return Optional.of(springSecurityContext.getCurrentWalletId())
                .map(withdrawRequest::withWalletId)
                .map(walletMapper::withdrawRequestToWithdrawInput)
                .map(withdrawUseCase::execute)
                .map(wallet -> ResponseEntity.status(HttpStatus.CREATED).body(ApiDefaultResponse.created(wallet)))
                .orElseThrow(() -> new RuntimeException("Unexpected error in withdraw"));
    }

}
