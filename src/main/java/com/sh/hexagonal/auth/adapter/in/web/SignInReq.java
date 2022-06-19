package com.sh.hexagonal.auth.adapter.in.web;

import com.sh.hexagonal.auth.application.port.in.SignInCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInReq {

    @Schema(description = "유저아이디", example = "userid", nullable = false) @NotBlank
    private final String userId;

    @Schema(description = "비밀번호", example = "password", nullable = false) @NotBlank
    private final String password;

    SignInCommand toCommand() {
        return new SignInCommand(userId, password);
    }
}
