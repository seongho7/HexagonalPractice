package com.sh.hexagonal.refund.adapter.out.sh;

import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapInfo;
import com.sh.hexagonal.refund.application.port.out.ScrapUserIncomePort;
import org.springframework.stereotype.Component;

@Component
public class ScrapUserIncomeShAdapter implements ScrapUserIncomePort {
    private final ShClient shClient;

    public ScrapUserIncomeShAdapter(ShClient shClient) {
        this.shClient = shClient;
    }

    @Override
    public UserIncomeScrapInfo scrapUserIncome(String name, String regNo) {
        var userScrapDto = shClient.scrapUserIncome(new GetUserIncomeDto(name, regNo));
        return userScrapDto.toUserIncomeScrapInfo();
    }
}
