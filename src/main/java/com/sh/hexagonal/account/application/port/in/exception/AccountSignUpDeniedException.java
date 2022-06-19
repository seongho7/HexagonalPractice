package com.sh.hexagonal.account.application.port.in.exception;

import com.sh.hexagonal.common.exception.InvalidParamException;
import com.sh.hexagonal.common.response.ErrorCode;

public class AccountSignUpDeniedException extends InvalidParamException {
    public AccountSignUpDeniedException() {
        super(ErrorCode.ACCOUNT_SIGN_UP_DENIED);
    }
}
