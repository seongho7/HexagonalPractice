package com.sh.hexagonal.auth.application.port.out;

import com.sh.hexagonal.account.domain.Account;

import java.util.Optional;

public interface LoadAccountPort {
    Optional<Account> loadAccountByUserId(String userId);
    Optional<Account> loadAccountById(long accountId);
}
