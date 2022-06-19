package com.sh.hexagonal.refund.application.port.out;

import com.sh.hexagonal.refund.domain.UserIncome;

import java.util.Optional;

public interface LoadUserIncomePort {
    Optional<UserIncome> loadByAccountId(long accountId);
}
