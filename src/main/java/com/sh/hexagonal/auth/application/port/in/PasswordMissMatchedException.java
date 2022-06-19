package com.sh.hexagonal.auth.application.port.in;

import com.sh.hexagonal.common.exception.BaseException;
import com.sh.hexagonal.common.response.ErrorCode;

public class PasswordMissMatchedException extends BaseException {
    public PasswordMissMatchedException() {
        super(ErrorCode.ACCOUNT_PASSWORD_MISS_MATCHED);
    }
}
