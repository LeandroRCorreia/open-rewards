package com.rewards.openrewards.modules.wallet.business.usecase;

import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.business.dto.DepositInput;
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
public class DepositUseCase implements UseCase<DepositInput, Wallet> {

    private final WalletGateway walletGateway;
    private final TransactionGateway transactionGateway;

    @Override
    @Transactional
    @CacheEvict(value = "wallet-balance", key = "#input.walletId()")
    public Wallet execute(DepositInput input) {
        if (input.isDepositZero()) {
            throw new BusinessException("Deposit amount must be greater than zero", "INVALID_DEPOSIT_AMOUNT", HttpStatus.BAD_REQUEST);
        }
        Wallet wallet = findWalletWithLockOrFail(input);

        Transaction transaction = Transaction.createDeposit(wallet.getId(), input.amount(), input.description(), input.idempotencyKey());
        Wallet walletWithDeposit = wallet.deposit(input.amount());
        transactionGateway.save(transaction);

        return walletGateway.update(walletWithDeposit);
    }

    private Wallet findWalletWithLockOrFail(DepositInput input){
        return walletGateway.findWalletWithLock(input.walletId())
                .orElseThrow(() -> new BusinessException("Wallet not found", "WALLET_NOT_FOUND", HttpStatus.NOT_FOUND));
    }

}
