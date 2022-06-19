package com.sh.hexagonal.account.application.port.in;

import com.sh.hexagonal.account.application.port.in.exception.AccountSignUpDeniedException;
import com.sh.hexagonal.account.application.port.in.exception.UserIdDuplicateException;

public interface SignUpUseCase {
    void signUp(SignUpCommand command) throws AccountSignUpDeniedException, UserIdDuplicateException;
}
