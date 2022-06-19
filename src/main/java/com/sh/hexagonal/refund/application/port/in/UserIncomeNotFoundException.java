package com.sh.hexagonal.refund.application.port.in;

import com.sh.hexagonal.common.exception.BaseException;
import com.sh.hexagonal.common.response.ErrorCode;

public final class UserIncomeNotFoundException extends BaseException {
    public UserIncomeNotFoundException() {
        super(ErrorCode.REFUND_USER_INCOME_NOT_FOUND);
    }
}
