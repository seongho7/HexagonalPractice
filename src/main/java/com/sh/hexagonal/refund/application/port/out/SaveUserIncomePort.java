package com.sh.hexagonal.refund.application.port.out;

import com.sh.hexagonal.refund.domain.UserIncome;

public interface SaveUserIncomePort {
    UserIncome save(UserIncome userIncome);
}
