package com.sh.hexagonal.refund.application.port.in;

import com.sh.hexagonal.common.Money;
import lombok.Value;

@Value
public class UserIncomeScrapInfo {
    Money calculatedTaxAmount;
    Money grossSalary;

    public UserIncomeScrapInfo(Money calculatedTaxAmount, Money grossSalary) {
        this.calculatedTaxAmount = calculatedTaxAmount;
        this.grossSalary = grossSalary;
    }
}
