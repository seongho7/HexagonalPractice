package com.sh.hexagonal.refund.adapter.out.persistence;

import com.sh.hexagonal.refund.application.port.out.LoadUserIncomePort;
import com.sh.hexagonal.refund.application.port.out.SaveUserIncomePort;
import com.sh.hexagonal.refund.domain.UserIncome;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserIncomePersistenceAdapter implements SaveUserIncomePort, LoadUserIncomePort {
    private final UserIncomeJpaRepository userIncomeJpaRepository;

    @Override
    public UserIncome save(UserIncome userIncome) {
        return userIncomeJpaRepository.save(userIncome);
    }

    @Override
    public Optional<UserIncome> loadByAccountId(long accountId) {
        return userIncomeJpaRepository.findById(accountId);
    }
}
