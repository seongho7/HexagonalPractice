package com.sh.hexagonal.account.adapter.in.web;

import com.sh.hexagonal.account.application.port.in.SignUpCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class SignUpReq {

    @Schema(description = "유저아이디", example = "userid", nullable = false) @NotBlank
     String userId;

    @Schema(description = "비밀번호", example = "password", nullable = false) @NotBlank
     String password;

    @Schema(description = "유저명", example = "삼쩜상", nullable = false) @NotBlank
     String name;

    @Schema(description = "주민등록번호", example = "860824-1655068", nullable = false) @NotBlank
     String regNo;

    SignUpCommand toCommand() {
        return new SignUpCommand(userId, password, name, regNo);
    }
}
