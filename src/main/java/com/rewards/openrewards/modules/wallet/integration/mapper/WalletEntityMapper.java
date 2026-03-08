package com.rewards.openrewards.modules.wallet.integration.mapper;


import com.rewards.openrewards.modules.wallet.business.domain.Wallet;
import com.rewards.openrewards.modules.wallet.integration.entity.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletEntityMapper {


    public Wallet toDomain(WalletEntity walletEntity){
        return Wallet.builder()
                .id(walletEntity.getId())
                .userId(walletEntity.getUserId())
                .balance(walletEntity.getBalance())
                .updatedAt(walletEntity.getUpdatedAt())
                .build();
    }

    public WalletEntity toEntity(Wallet wallet){
        return WalletEntity.builder()
                .id(wallet.getId())
                .userId(wallet.getUserId())
                .balance(wallet.getBalance())
                .updatedAt(wallet.getUpdatedAt())
                .build();
    }

}
