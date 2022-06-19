package com.sh.hexagonal.refund.application.service;

import com.sh.hexagonal.refund.application.port.in.CalculateRefundUseCase;
import com.sh.hexagonal.refund.application.port.in.UserIncomeNotFoundException;
import com.sh.hexagonal.refund.application.port.out.LoadUserIncomePort;
import com.sh.hexagonal.refund.domain.TaxCreditLimitCalculator;
import com.sh.hexagonal.refund.domain.UserIncome;
import com.sh.hexagonal.refund.domain.UserRefund;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CalculateRefundService implements CalculateRefundUseCase {
    private final LoadUserIncomePort loadUserIncomePort;
    private final List<TaxCreditLimitCalculator> taxCreditLimitCalculators;

    @Override
    public UserRefund calculate(long accountId) {
        var userIncome = loadUserIncome(accountId);
        var userRefund = userIncome.calculateRefund(taxCreditLimitCalculators);

        return userRefund;
    }

    private UserIncome loadUserIncome(long accountId) {
        var oUserIncome = loadUserIncomePort.loadByAccountId(accountId);
        if(oUserIncome.isEmpty()) {
            throw new UserIncomeNotFoundException();
        }
        return oUserIncome.get();
    }
}
