package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.auth.domain.AccessToken;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TokenProviderSpy implements TokenProvider{
    private final Map<String, Long> data = new ConcurrentHashMap<>();

    @Override
    public AccessToken createToken(Account account) {
        String token = UUID.randomUUID().toString();
        data.put(token, account.getId());
        return new AccessToken(account, token, LocalDateTime.MAX);
    }

//    @Override
//    public long getAccountIdFromToken(String token) {
//        return data.get(token);
//    }
}
