package com.sh.hexagonal.refund.adapter.in.web;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.common.STag;
import com.sh.hexagonal.common.response.CommonResponse;
import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapCommand;
import com.sh.hexagonal.refund.application.port.in.UserIncomeScrapUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = STag.REFUND)
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RestController
public class UserIncomeScrapController {
    private final UserIncomeScrapUseCase userIncomeScrapUseCase;

    @Operation(
            summary = "유저정보 스크랩",
            description = "가입한 유저의 소득 정보를 스크랩합니다."
    )
    @PostMapping("/sh/scrap")
    CommonResponse scrap(@AuthenticationPrincipal Account account) {
        userIncomeScrapUseCase.scrap(new UserIncomeScrapCommand(account.getId(), account.getName(), account.getRegNo()));
        return CommonResponse.success();
    }
}
