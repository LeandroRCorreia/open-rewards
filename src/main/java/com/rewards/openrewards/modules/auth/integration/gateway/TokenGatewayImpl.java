package com.rewards.openrewards.modules.auth.integration.gateway;

import com.rewards.openrewards.modules.auth.business.gateway.TokenGateway;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class TokenGatewayImpl implements TokenGateway {

    @Value("${api.security.token.secret:uma-chave-secreta-muito-forte-com-mais-de-32-caracteres-aqui-1234}")
    private String secretKey;

    @Value("${api.security.token.expiration:7200000}")
    private Long expirationTime;

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
