package com.sh.hexagonal.account.adapter.in.web;

import com.sh.hexagonal.account.application.port.in.SignUpUseCase;
import com.sh.hexagonal.common.STag;
import com.sh.hexagonal.common.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = STag.ACCOUNT)
@RestController
@RequiredArgsConstructor
public final class SignUpController {
    private final SignUpUseCase signUpUseCase;

    @Operation(
            summary = "회원 가입",
            description = "회원 가입"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "ACCOUNT_SIGN_UP_DENIED", description = "가입할수 없는 회원"),
            @ApiResponse(responseCode = "ACCOUNT_USER_ID_DUPLICATE", description = "동일한 유저아이디가 있음")
    })
    @PostMapping("/sh/signup")
    CommonResponse<Void> singUp(@RequestBody SignUpReq dto) {
        signUpUseCase.signUp(dto.toCommand());
        return CommonResponse.success();
    }
}
