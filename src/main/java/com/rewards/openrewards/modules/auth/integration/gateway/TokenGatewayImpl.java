package com.rewards.openrewards.modules.auth.integration.gateway;

import com.rewards.openrewards.modules.auth.business.domain.AuthCredentials;
import com.rewards.openrewards.modules.auth.business.enums.UserRoles;
import com.rewards.openrewards.modules.auth.business.gateway.TokenGateway;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class TokenGatewayImpl implements TokenGateway {

    @Value("${api.security.token.secret:uma-chave-secreta-muito-forte-com-mais-de-32-caracteres-aqui-1234}")
    private String secretKey;

    @Value("${api.security.token.expiration:7200000}")
    private Long expirationTime;

    @Override
    public String generateToken(UserDetails userDetails) {
        AuthCredentials authCredentials = (AuthCredentials) userDetails;

        return Jwts.builder()
                .claim("id", authCredentials.getId())
                .claim("roles", authCredentials.getAuthorities().stream().findFirst().get().getAuthority())
                .subject(userDetails.getUsername())
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
                    .roles(UserRoles.fromString(claims.get("roles", String.class)))
                    .build();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Token JWT inválido ou expirado", e);
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
