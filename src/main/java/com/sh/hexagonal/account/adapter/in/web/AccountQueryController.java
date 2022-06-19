package com.sh.hexagonal.account.adapter.in.web;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.common.STag;
import com.sh.hexagonal.common.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = STag.ACCOUNT)
@PreAuthorize("isAuthenticated()")
@RestController
public class AccountQueryController {

    @Operation(
            summary = "내정보 조회",
            description = "인증토큰을 이용하여 본인정보를 조회합니다."
    )
    @GetMapping("/sh/me")
    CommonResponse<AccountRes> getMyInfo(@AuthenticationPrincipal Account account) {
        return CommonResponse.success(new AccountRes(account));
    }
}
