package com.sh.hexagonal.refund.adapter.in.web;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.common.STag;
import com.sh.hexagonal.common.response.CommonResponse;
import com.sh.hexagonal.refund.application.port.in.CalculateRefundUseCase;
import com.sh.hexagonal.refund.domain.UserRefund;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = STag.REFUND)
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RestController
public class RefundCalculateController {
    private final CalculateRefundUseCase calculateRefundUseCase;

    @Operation(
            summary = "환급액 계산산",
           description = "유저의 환급액을 계산합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "REFUND_USER_INCOME_NOT_FOUND", description = "유저 스크랩 정보가 없음")
    })
    @GetMapping("/sh/refund")
    CommonResponse<UserRefundRes> scrap(@AuthenticationPrincipal Account account) {
        UserRefund userRefund = calculateRefundUseCase.calculate(account.getId());
        return CommonResponse.success(new UserRefundRes(account, userRefund));
    }
}
