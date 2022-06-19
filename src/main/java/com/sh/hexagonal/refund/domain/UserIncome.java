package com.sh.hexagonal.refund.domain;

import com.sh.hexagonal.common.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserIncome implements Serializable {
    @Id
    private Long accountId;

    private Long calculatedTaxAmount;   //산출세액

    private Long grossSalary;           //총 급여액

    private LocalDateTime createdAt;

    public UserIncome(Long accountId, Money calculatedTaxAmount, Money grossSalary) {
        this.accountId = accountId;
        this.calculatedTaxAmount = calculatedTaxAmount.getAmount().longValue();
        this.grossSalary = grossSalary.getAmount().longValue();
        this.createdAt = LocalDateTime.now();
    }

    public UserRefund calculateRefund(List<TaxCreditLimitCalculator> calculators) {
        Money taxCreditLimit = calculateTaxCreditLimit(calculators);
        Money taxDeduction = calculateTaxDeduction();
        Money refund = taxCreditLimit.isGreaterThan(taxDeduction) ? taxDeduction : taxCreditLimit;
        return new UserRefund(taxCreditLimit, taxDeduction, refund);
    }

    private Money calculateTaxCreditLimit(List<TaxCreditLimitCalculator> calculators) {
        return calculators.stream()
                .filter(it->it.support(Money.of(grossSalary)))
                .findFirst().get()
                .calculate(Money.of(grossSalary));
    }

    private Money calculateTaxDeduction() {
        Money minimumTaxDeduction = Money.of(715000);
        Money baseAmount = Money.of(1300000);
        Money taxAmountMoney = Money.of(calculatedTaxAmount);

        if(taxAmountMoney.isGreaterThan(baseAmount)) {
            return minimumTaxDeduction.plus(taxAmountMoney.minus(baseAmount).multiply(30).divide(100)).floor();
        }
        return taxAmountMoney.multiply(55).divide(100).floor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIncome that = (UserIncome) o;
        return accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}
