package com.sh.hexagonal.refund.application.port.in;

import com.sh.hexagonal.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@EqualsAndHashCode(callSuper = false)
public class UserIncomeScrapCommand extends SelfValidating<UserIncomeScrapCommand> {
    @Getter @NotNull
    private final Long accountId;

    @NotBlank @Getter
    private final String name;
    @NotBlank @Getter
    private final String regNo;

    public UserIncomeScrapCommand(Long accountId, String name, String regNo) {
        this.accountId = accountId;
        this.name = name;
        this.regNo = regNo;
        this.validateSelf();
    }
}
