package com.rewards.openrewards.modules.auth.integration.gateway;

import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import com.rewards.openrewards.modules.auth.business.enums.UserRoles;
import com.rewards.openrewards.modules.auth.business.gateway.TokenGateway;
import com.rewards.openrewards.modules.auth.integration.security.AuthCredentialsDetails;
import com.rewards.openrewards.shared.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class TokenGatewayImpl implements TokenGateway {

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.expiration:7200000}")
    private Long expirationTime;

    @Override
    public String generateToken(AuthCredentials credentials) {
        return Jwts.builder()
                .claim("id", credentials.getId())
                .claim("userId", credentials.getUserId())
                .claim("walletId", credentials.getWalletId())
                .claim("roles", credentials.getRoles().name())
                .subject(credentials.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public AuthCredentials validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return AuthCredentials.builder()
                    .email(claims.getSubject())
                    .id(claims.get("id", Long.class))
                    .userId(claims.get("userId", Long.class))
                    .walletId(claims.get("walletId", Long.class))
                    .roles(UserRoles.fromString(claims.get("roles", String.class)))
                    .build();
        } catch (JwtException | IllegalArgumentException e) {
            throw new BusinessException(
                    "INVALID_TOKEN",
                    "Token JWT inválido ou expirado",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
