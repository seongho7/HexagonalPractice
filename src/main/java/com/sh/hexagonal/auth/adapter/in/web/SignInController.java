package com.sh.hexagonal.auth.adapter.in.web;

import com.sh.hexagonal.auth.application.port.in.SignInUseCase;
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

@Tag(name = STag.AUTH)
@RestController
@RequiredArgsConstructor
public class SignInController {
    private final SignInUseCase signInUseCase;

    @Operation(
            summary = "로그인",
            description = "해당 API를 통해 token을 발급합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "ACCOUNT_PASSWORD_MISS_MATCHED", description = "비밀번호가 틀렸음"),
            @ApiResponse(responseCode = "ACCOUNT_NOT_FOUND", description = "계정이 없음")
    })
    @PostMapping("/sh/login")
    CommonResponse<AccessTokenRes> signIn(@RequestBody SignInReq dto) {
        var accessToken = signInUseCase.signIn(dto.toCommand());
        return CommonResponse.success(new AccessTokenRes(accessToken));
    }
}
