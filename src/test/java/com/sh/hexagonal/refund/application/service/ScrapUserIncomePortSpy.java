package com.sh.hexagonal.refund.application.service;

import com.sh.hexagonal.common.Money;
import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapInfo;
import com.sh.hexagonal.refund.application.port.out.ScrapUserIncomePort;

public class ScrapUserIncomePortSpy implements ScrapUserIncomePort {
    @Override
    public UserIncomeScrapInfo scrapUserIncome(String name, String regNo) {
        return new UserIncomeScrapInfo(Money.of(1_000_000), Money.of(24_000_000));
    }
}
