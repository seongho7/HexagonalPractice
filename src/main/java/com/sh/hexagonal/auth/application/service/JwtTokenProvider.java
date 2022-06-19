package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.auth.domain.AccessToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.ZoneId;
import java.util.Date;

@Component
public final class JwtTokenProvider implements TokenProvider{
    private final String tokenSecret;
    private final long tokenExpirationMillis;

    public JwtTokenProvider(@Value("${app.auth.token-secret}") String tokenSecret,
                            @Value("${app.auth.token-expiration-msec}") long tokenExpirationMillis) {
        this.tokenSecret = tokenSecret;
        this.tokenExpirationMillis = tokenExpirationMillis;
    }

    @Override
    public AccessToken createToken(Account account) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenExpirationMillis);

        String token = Jwts.builder().signWith(getSecretKey())
                .setSubject(Long.toString(account.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .compact();
        return new AccessToken(account, token, expiryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

//    @Override
//    public long getAccountIdFromToken(String token) {
//        var subject = Jwts.parserBuilder()
//                .setSigningKey(getSecretKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody().getSubject();
//        return Long.parseLong(subject);
//    }

    private SecretKey getSecretKey() {
        var keyBytes = Decoders.BASE64.decode(tokenSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
