package com.sh.hexagonal.refund.domain;

import com.sh.hexagonal.common.Money;

public interface TaxCreditLimitCalculator {

    boolean support(Money grossSalary);
    Money calculate(Money grossSalary);
}
