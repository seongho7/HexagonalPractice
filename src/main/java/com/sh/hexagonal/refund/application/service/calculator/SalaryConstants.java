package com.sh.hexagonal.refund.application.service.calculator;

import com.sh.hexagonal.common.Money;

public class SalaryConstants {
    static final Money LEVEL_ONE_SALARY = Money.of(33000000);
    static final Money LEVEL_TWO_SALARY = Money.of(70000000);

    static final Money LEVEL_ONE_MINIMUM_TAX = Money.of(740000);
    static final Money LEVEL_TWO_MINIMUM_TAX = Money.of(660000);
    static final Money LEVEL_THREE_MINIMUM_TAX = Money.of(500000);
}
