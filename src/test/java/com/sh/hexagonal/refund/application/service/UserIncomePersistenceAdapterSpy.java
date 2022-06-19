package com.sh.hexagonal.refund.application.service;

import com.sh.hexagonal.refund.application.port.out.LoadUserIncomePort;
import com.sh.hexagonal.refund.application.port.out.SaveUserIncomePort;
import com.sh.hexagonal.refund.domain.UserIncome;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserIncomePersistenceAdapterSpy implements SaveUserIncomePort, LoadUserIncomePort {
    private final Map<Long, UserIncome> data = new ConcurrentHashMap<>();

    @Override
    public Optional<UserIncome> loadByAccountId(long accountId) {
        return Optional.of(data.get(accountId));
    }

    @Override
    public UserIncome save(UserIncome userIncome) {
        data.put(userIncome.getAccountId(), userIncome);
        return userIncome;
    }
}
