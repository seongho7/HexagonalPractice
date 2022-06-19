package com.sh.hexagonal.account.application.port.in;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.common.SelfValidating;
import com.sh.hexagonal.common.id.IdGenerator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@EqualsAndHashCode(callSuper = false)
public final class SignUpCommand extends SelfValidating<SignUpCommand> {

    @NotBlank @Getter
    private final String userId;

    @NotBlank @Getter
    private final String password;

    @NotBlank @Getter
    private final String name;

    @NotBlank@NotNull @Getter
    private final String regNo;

    @Builder
    public SignUpCommand(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
        this.validateSelf();
    }

    public Account toAccount(IdGenerator idGenerator, PasswordEncoder passwordEncoder) {
        return new Account(idGenerator.nextId(), userId, passwordEncoder.encode(password), name, regNo);
    }
}
