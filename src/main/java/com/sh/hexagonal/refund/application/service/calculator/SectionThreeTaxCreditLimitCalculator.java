package com.sh.hexagonal.refund.application.service.calculator;

import com.sh.hexagonal.common.Money;
import com.sh.hexagonal.refund.domain.TaxCreditLimitCalculator;
import org.springframework.stereotype.Component;

import static com.sh.hexagonal.refund.application.service.calculator.SalaryConstants.*;

@Component
public class SectionThreeTaxCreditLimitCalculator implements TaxCreditLimitCalculator {
    @Override
    public boolean support(Money grossSalary) {
        return grossSalary.isGreaterThan(SalaryConstants.LEVEL_TWO_SALARY);
    }

    @Override
    public Money calculate(Money grossSalary) {
        Money calculatedTax = LEVEL_TWO_MINIMUM_TAX.minus(
                grossSalary.minus(LEVEL_TWO_SALARY).divide(2)
        );
        if(calculatedTax.isGreaterThan(LEVEL_THREE_MINIMUM_TAX)) {
            return calculatedTax;
        }

        return LEVEL_THREE_MINIMUM_TAX;
    }
}
