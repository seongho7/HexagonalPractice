package com.sh.hexagonal.auth.application.port.in;

import com.sh.hexagonal.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@EqualsAndHashCode(callSuper = false)
public class SignInCommand extends SelfValidating<SignInCommand> {

    @NotNull @Getter
    private final String userId;

    @NotNull @Getter
    private final String password;

    public SignInCommand(String userId, String password) {
        this.userId = userId;
        this.password = password;
        super.validateSelf();
    }
}
