package com.sh.hexagonal.account.adapter.in.web;

import com.sh.hexagonal.account.domain.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class AccountRes {
    @Schema(description = "계정번호", example = "1", nullable = false) @NotBlank
     Long id;

    @Schema(description = "유저아이디", example = "userid", nullable = false) @NotBlank
     String userId;

    @Schema(description = "유저명", example = "삼쩜상", nullable = false) @NotBlank
     String name;

    @Schema(description = "주민등록번호", example = "860824-1655068", nullable = false) @NotBlank
     String regNo;

    public AccountRes(Account account) {
        this.id = account.getId();
        this.userId = account.getUserId();
        this.name = account.getName();
        this.regNo = account.getRegNo();
    }
}
