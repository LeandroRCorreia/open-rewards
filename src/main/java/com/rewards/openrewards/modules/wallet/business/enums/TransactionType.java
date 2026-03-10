package com.rewards.openrewards.modules.wallet.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    CASH_IN(1, "CASH_IN"),
    DEPOSIT(2, "DEPOSIT");

    private final int code;
    private final String description;
}
