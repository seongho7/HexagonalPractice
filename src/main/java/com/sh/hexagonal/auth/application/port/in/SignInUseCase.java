package com.sh.hexagonal.auth.application.port.in;

import com.sh.hexagonal.auth.domain.AccessToken;

public interface SignInUseCase {
    AccessToken signIn(SignInCommand command) throws PasswordMissMatchedException, AccountNotFoundException;
}
