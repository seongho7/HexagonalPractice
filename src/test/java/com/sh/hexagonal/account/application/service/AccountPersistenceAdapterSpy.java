package com.sh.hexagonal.account.application.service;

import com.sh.hexagonal.account.application.port.out.SaveAccountPort;
import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.auth.application.port.out.LoadAccountPort;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AccountPersistenceAdapterSpy implements LoadAccountPort, SaveAccountPort {

    private final Map<Long, Account> data = new ConcurrentHashMap<>();

    @Override
    public Optional<Account> loadAccountByUserId(String userId) {
        return data.values().stream()
                .filter(account -> userId.equals(account.getUserId()))
                .findAny();
    }

    @Override
    public Optional<Account> loadAccountById(long accountId) {
        return Optional.of(data.get(accountId));
    }

    @Override
    public Account save(Account account) {
        data.put(account.getId(), account);
        return account;
    }
}
