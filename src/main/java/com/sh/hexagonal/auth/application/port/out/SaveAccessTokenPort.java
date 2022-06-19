package com.sh.hexagonal.auth.application.port.out;

import com.sh.hexagonal.auth.domain.AccessToken;

public interface SaveAccessTokenPort {
    AccessToken save(AccessToken token);
}
