package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.auth.application.port.in.AccountNotFoundException;
import com.sh.hexagonal.auth.application.port.in.PasswordMissMatchedException;
import com.sh.hexagonal.auth.application.port.in.SignInCommand;
import com.sh.hexagonal.auth.application.port.in.SignInUseCase;
import com.sh.hexagonal.auth.application.port.out.LoadAccountPort;
import com.sh.hexagonal.auth.application.port.out.SaveAccessTokenPort;
import com.sh.hexagonal.auth.domain.AccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class SignInService implements SignInUseCase {
    private final LoadAccountPort loadAccountPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final SaveAccessTokenPort saveAccessTokenPort;

    @Override
    public AccessToken signIn(SignInCommand command) throws PasswordMissMatchedException {
        var account = loadAccountPort.loadAccountByUserId(command.getUserId())
                .orElseThrow(AccountNotFoundException::new);

        if(!passwordEncoder.matches(command.getPassword(), account.getPassword())) {
            throw new PasswordMissMatchedException();
        }
        return issueToken(account);
    }

    private AccessToken issueToken(Account account) {
        var accessToken = tokenProvider.createToken(account);
        return saveAccessTokenPort.save(accessToken);
    }
}
