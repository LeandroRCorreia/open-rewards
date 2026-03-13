package com.rewards.openrewards.modules.wallet.business.usecase;

import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.dto.WithdrawInput;
import com.rewards.openrewards.modules.wallet.business.gateway.TransactionGateway;
import com.rewards.openrewards.modules.wallet.business.gateway.WalletGateway;
import com.rewards.openrewards.shared.BusinessException;
import com.rewards.openrewards.shared.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WithdrawUseCase implements UseCase<WithdrawInput, Wallet> {

    private final WalletGateway walletGateway;
    private final TransactionGateway transactionGateway;

    @Override
    @Transactional
    @CacheEvict(value = "wallet-balance", key = "#input.walletId()")
    public Wallet execute(WithdrawInput input) {
        if(input.isInvalidAmount()){
            throw new BusinessException(
                    "Withdraw amount must be greater than zero",
                    "INVALID_WITHDRAW_AMOUNT",
                    HttpStatus.BAD_REQUEST
            );
        }

        Wallet wallet = findWalletWithLockOrFail(input);
        if(wallet.isNotSufficientBalance(input.amount())){
            throw new BusinessException(
                    "Insufficient balance",
                    "INSUFFICIENT_BALANCE",
                    HttpStatus.CONFLICT
            );
        }
        Wallet withdrawedWallet = wallet.withdraw(input.amount());

        Transaction transaction = Transaction.createWithdraw(wallet.getId(), input.amount(), input.description(), input.idempotencyKey());

        transactionGateway.save(transaction);

        return walletGateway.update(withdrawedWallet);
    }

    private Wallet findWalletWithLockOrFail(WithdrawInput input) {
        return walletGateway.findWalletWithLock(input.walletId())
                .orElseThrow(() -> new BusinessException("Wallet not found", "WALLET_NOT_FOUND", HttpStatus.NOT_FOUND));
    }

}
