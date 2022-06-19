package com.sh.hexagonal.auth.application.port.out;

import com.sh.hexagonal.auth.domain.AccessToken;

import java.util.Optional;

public interface LoadAccessTokenPort {
    Optional<AccessToken> loadByToken(String token);
}
