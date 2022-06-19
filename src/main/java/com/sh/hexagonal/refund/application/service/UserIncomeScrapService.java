package com.sh.hexagonal.refund.application.service;

import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapCommand;
import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapInfo;
import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapUseCase;
import com.sh.hexagonal.refund.application.port.out.SaveUserIncomePort;
import com.sh.hexagonal.refund.application.port.out.ScrapUserIncomePort;
import com.sh.hexagonal.refund.domain.UserIncome;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserIncomeScrapService implements UserIncomeScrapUseCase {
    private final ScrapUserIncomePort scrapUserIncomePort;
    private final SaveUserIncomePort saveUserIncomePort;

    @Async
    @Override
    public void scrap(UserIncomeScrapCommand command) {
        UserIncomeScrapInfo info = scrapUserIncomePort.scrapUserIncome(command.getName(), command.getRegNo());
        var userIncome = new UserIncome(command.getAccountId(), info.getCalculatedTaxAmount(), info.getGrossSalary());
        saveUserIncomePort.save(userIncome);
    }
}
