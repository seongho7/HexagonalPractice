package com.sh.hexagonal.refund.application.service.calculator;

import com.sh.hexagonal.common.Money;
import com.sh.hexagonal.refund.domain.TaxCreditLimitCalculator;
import org.springframework.stereotype.Component;

import static com.sh.hexagonal.refund.application.service.calculator.SalaryConstants.*;

@Component
public class SectionTwoTaxCreditLimitCalculator implements TaxCreditLimitCalculator {
    @Override
    public boolean support(Money grossSalary) {
        return grossSalary.isGreaterThan(LEVEL_ONE_SALARY)
                && LEVEL_TWO_SALARY.isGreaterThanOrEqualTo(grossSalary);
    }

    @Override
    public Money calculate(Money grossSalary) {
        Money calculatedTax = LEVEL_ONE_MINIMUM_TAX.minus(
                grossSalary.minus(LEVEL_ONE_SALARY).multiply(0.008)
        ).floor();

        if(calculatedTax.isGreaterThan(LEVEL_TWO_MINIMUM_TAX)) {
            return calculatedTax;
        }
        return LEVEL_TWO_MINIMUM_TAX;
    }
}
