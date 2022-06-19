package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.auth.application.port.out.SaveAccessTokenPort;
import com.sh.hexagonal.auth.domain.AccessToken;
import org.springframework.security.util.FieldUtils;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class AccessTokenPersistenceAdapterSpy implements SaveAccessTokenPort {

    private final Map<Long, AccessToken> data = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public AccessToken save(AccessToken token) {
        FieldUtils.setProtectedFieldValue("id", token, idGenerator.incrementAndGet());
        data.put(token.getId(), token);
        return token;
    }

    public Optional<AccessToken> loadById(Long accessTokenId) {
        return Optional.of(data.get(accessTokenId));
    }
}
