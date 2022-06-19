package com.sh.hexagonal.refund.application.service.calculator;

import com.sh.hexagonal.common.Money;
import com.sh.hexagonal.refund.domain.TaxCreditLimitCalculator;
import org.springframework.stereotype.Component;

import static com.sh.hexagonal.refund.application.service.calculator.SalaryConstants.*;

@Component
public class SectionOneTaxCreditLimitCalculator implements TaxCreditLimitCalculator {

    @Override
    public boolean support(Money grossSalary) {
        return LEVEL_ONE_SALARY.isGreaterThanOrEqualTo(grossSalary);
    }

    @Override
    public Money calculate(Money grossSalary) {
        return LEVEL_ONE_MINIMUM_TAX;
    }
}
