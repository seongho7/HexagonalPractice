package com.sh.hexagonal.refund.application.service;

import com.sh.hexagonal.common.Money;
import com.sh.hexagonal.refund.application.service.calculator.SectionOneTaxCreditLimitCalculator;
import com.sh.hexagonal.refund.application.service.calculator.SectionThreeTaxCreditLimitCalculator;
import com.sh.hexagonal.refund.application.service.calculator.SectionTwoTaxCreditLimitCalculator;
import com.sh.hexagonal.refund.domain.UserIncome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateRefundServiceTest {

    @TestFactory
    @DisplayName("유저의 환급액을 계산한다")
    Collection<DynamicTest> signup_use_case() {
        var persistenceAdapter = new UserIncomePersistenceAdapterSpy();

        persistenceAdapter.save(new UserIncome(1L, Money.of(2_000_000), Money.of(24_000_000)));
        persistenceAdapter.save(new UserIncome(2L, Money.of(1_000_000), Money.of(24_000_000)));
        persistenceAdapter.save(new UserIncome(3L, Money.of(2_000_000), Money.of(40_000_000)));
        persistenceAdapter.save(new UserIncome(4L, Money.of(3_000_000), Money.of(41_333_333)));
        persistenceAdapter.save(new UserIncome(5L, Money.of(1_333_333_333), Money.of(94_666_666)));

        var calculators = Arrays.asList(new SectionOneTaxCreditLimitCalculator(), new SectionTwoTaxCreditLimitCalculator(), new SectionThreeTaxCreditLimitCalculator());
        var calculateRefundService = new CalculateRefundService(persistenceAdapter, calculators);

        return Arrays.asList(
                DynamicTest.dynamicTest("총 급여 24_000_000, 산출세액 2_000_000 일경우를 계산한다.", ()->{
                    var refund = calculateRefundService.calculate(1L);
                    assertEquals(Money.of(740_000), refund.getTaxCreditLimit());
                    assertEquals(Money.of(925_000), refund.getTaxDeduction());
                    assertEquals(Money.of(740_000), refund.getRefund());
                }),

                DynamicTest.dynamicTest("총 급여 24_000_000, 산출세액 1_000_000 일경우를 계산한다.", ()->{
                    var refund = calculateRefundService.calculate(2L);
                    assertEquals(Money.of(740_000), refund.getTaxCreditLimit());
                    assertEquals(Money.of(550_000), refund.getTaxDeduction());
                    assertEquals(Money.of(550_000), refund.getRefund());
                }),

                DynamicTest.dynamicTest("총 급여 40_000_000, 산출세액 2_000_000 일경우를 계산한다.", ()->{
                    var refund = calculateRefundService.calculate(3L);
                    assertEquals(Money.of(684_000), refund.getTaxCreditLimit());
                    assertEquals(Money.of(925_000), refund.getTaxDeduction());
                    assertEquals(Money.of(684_000), refund.getRefund());
                }),

                DynamicTest.dynamicTest("총 급여 41_333_333, 산출세액 3_000_000 일경우를 계산한다.", ()->{
                    var refund = calculateRefundService.calculate(4L);
                    assertEquals(Money.of(673_333), refund.getTaxCreditLimit());
                    assertEquals(Money.of(1_225_000), refund.getTaxDeduction());
                    assertEquals(Money.of(673_333), refund.getRefund());
                }),

                DynamicTest.dynamicTest("총 급여 94_666_666, 산출세액 1_333_333_333 일경우를 계산한다.", ()->{
                    var refund = calculateRefundService.calculate(5L);
                    assertEquals(Money.of(500_000), refund.getTaxCreditLimit());
                    assertEquals(Money.of(400_324_999), refund.getTaxDeduction());
                    assertEquals(Money.of(500_000), refund.getRefund());
                })
        );
    }

}