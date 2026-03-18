package com.rewards.openrewards.modules.auth.integration.mapper;


import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import com.rewards.openrewards.modules.auth.integration.entity.AuthCredentialsEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthGatewayEntityMapper {


    public AuthCredentials toDomain(AuthCredentialsEntity entity) {
        return AuthCredentials.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .passwordHash(entity.getPasswordHash())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public AuthCredentialsEntity toEntity(AuthCredentials domain) {
        return AuthCredentialsEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .email(domain.getEmail())
                .passwordHash(domain.getPasswordHash())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
