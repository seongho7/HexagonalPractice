package com.sh.hexagonal.refund.application.service;

import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserIncomeScrapServiceTest {

    @DisplayName("유저정보 스크랩 유스케이스")
    @Test
    void scrap() {
        var scrapUserIncomePortSpy = new ScrapUserIncomePortSpy();
        var persistenceAdapter = new UserIncomePersistenceAdapterSpy();
        var service = new UserIncomeScrapService(scrapUserIncomePortSpy, persistenceAdapter);

        var command = new UserIncomeScrapCommand(1L, "네이버", "901010-1234567");
        service.scrap(command);
        var oUserIncome = persistenceAdapter.loadByAccountId(command.getAccountId());
        assertTrue(oUserIncome.isPresent());
    }
}