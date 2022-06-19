package com.sh.hexagonal.auth.adapter.out.persistence;

import com.sh.hexagonal.auth.application.port.out.LoadAccessTokenPort;
import com.sh.hexagonal.auth.application.port.out.SaveAccessTokenPort;
import com.sh.hexagonal.auth.domain.AccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccessTokenPersistenceAdapter implements SaveAccessTokenPort, LoadAccessTokenPort {
    private final AccessTokenJpaRepository accessTokenJpaRepository;

    @Override
    public AccessToken save(AccessToken token) {
        return accessTokenJpaRepository.save(token);
    }

    @Override
    public Optional<AccessToken> loadByToken(String token) {
        return accessTokenJpaRepository.findByToken(token);
    }
}
