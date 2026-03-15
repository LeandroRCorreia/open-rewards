package com.rewards.openrewards.modules.wallet.business.usecase;

import com.rewards.openrewards.modules.wallet.business.domain.Transaction;
import com.rewards.openrewards.modules.wallet.business.dto.GetTransactionInput;
import com.rewards.openrewards.modules.wallet.business.gateway.TransactionGateway;
import com.rewards.openrewards.shared.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class StatementUseCase implements UseCase<GetTransactionInput, Page<Transaction>> {

    private final TransactionGateway transactionGateway;

    @Override
    public Page<Transaction> execute(GetTransactionInput input) {
        return transactionGateway.findAllStatement(input.walletId(), input.pageable());
    }
}
