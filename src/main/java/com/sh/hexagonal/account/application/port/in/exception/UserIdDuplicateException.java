package com.sh.hexagonal.account.application.port.in.exception;


import com.sh.hexagonal.common.exception.BaseException;
import com.sh.hexagonal.common.response.ErrorCode;

public class UserIdDuplicateException extends BaseException {
    public UserIdDuplicateException() {
        super(ErrorCode.ACCOUNT_USER_ID_DUPLICATE);
    }
}
