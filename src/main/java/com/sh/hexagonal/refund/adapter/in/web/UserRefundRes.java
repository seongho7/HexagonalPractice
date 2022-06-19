package com.sh.hexagonal.refund.adapter.in.web;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.common.util.HangulMoneyFormatter;
import com.sh.hexagonal.refund.domain.UserRefund;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class UserRefundRes {

    @Schema(description = "유저명", example = "삼쩜삼", nullable = false) @NotBlank
    String name;

    @Schema(description = "세액공제 한도", example = "password", nullable = false) @NotBlank
    String taxCreditLimit;

    @Schema(description = "세액 공제", example = "password", nullable = false) @NotBlank
    String taxDeduction;

    @Schema(description = "환급액", example = "password", nullable = false) @NotBlank
    String refund;

    public UserRefundRes(Account account, UserRefund userRefund) {
        this.name = account.getName();
        this.taxCreditLimit = new HangulMoneyFormatter(userRefund.getTaxCreditLimit()).format();
        this.taxDeduction = new HangulMoneyFormatter(userRefund.getTaxDeduction()).format();
        this.refund = new HangulMoneyFormatter(userRefund.getRefund()).format();
    }
}
