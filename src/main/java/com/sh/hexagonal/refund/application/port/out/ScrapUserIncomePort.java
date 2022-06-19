package com.sh.hexagonal.refund.application.port.out;

import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapInfo;

public interface ScrapUserIncomePort {
    UserIncomeScrapInfo scrapUserIncome(String name, String regNo);
}
