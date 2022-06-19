package com.sh.hexagonal.auth.application.port.in;

import com.sh.hexagonal.common.exception.BaseException;
import com.sh.hexagonal.common.response.ErrorCode;

public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException() {
        super(ErrorCode.ACCOUNT_NOT_FOUND);
    }
}
