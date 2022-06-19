package com.sh.hexagonal.account.application.service;

import com.sh.hexagonal.account.application.port.in.SignUpCommand;
import com.sh.hexagonal.account.application.port.in.SignUpUseCase;
import com.sh.hexagonal.account.application.port.in.exception.AccountSignUpDeniedException;
import com.sh.hexagonal.account.application.port.in.exception.UserIdDuplicateException;
import com.sh.hexagonal.account.application.port.out.SaveAccountPort;
import com.sh.hexagonal.auth.application.port.out.LoadAccountPort;
import com.sh.hexagonal.common.id.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public final class SignUpService implements SignUpUseCase {
    private final SaveAccountPort saveAccountPort;
    private final LoadAccountPort loadAccountPort;
    private final PasswordEncoder passwordEncoder;
    private final IdGenerator idGenerator;

    @Override
    public void signUp(SignUpCommand command) {
        validateUserNameAndRegNo(command.getName(), command.getRegNo());
        var oAccount = loadAccountPort.loadAccountByUserId(command.getUserId());
        if(oAccount.isPresent()) {
            throw new UserIdDuplicateException();
        }

        saveAccountPort.save(command.toAccount(idGenerator, passwordEncoder));
    }

    private static Map<String, String> accountMap = new HashMap<>();
    static {
        accountMap.put("901010-1234567", "네이버");
    }
    private void validateUserNameAndRegNo(String name, String regNo) {
//        if(StringUtils.hasText(name) && !name.equals(accountMap.get(regNo))) {
//            throw new AccountSignUpDeniedException();
//        }
    }
}
