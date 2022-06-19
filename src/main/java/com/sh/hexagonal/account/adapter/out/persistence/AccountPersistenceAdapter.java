package com.sh.hexagonal.account.adapter.out.persistence;

import com.sh.hexagonal.account.application.port.out.SaveAccountPort;
import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.auth.application.port.out.LoadAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public final class AccountPersistenceAdapter implements LoadAccountPort, SaveAccountPort {
    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Optional<Account> loadAccountByUserId(String userId) {
        return accountJpaRepository.findByUserId(userId);
    }

    @Override
    public Optional<Account> loadAccountById(long accountId) {
        return accountJpaRepository.findById(accountId);
    }

    @Override
    public Account save(Account account) {
        return accountJpaRepository.save(account);
    }
}
