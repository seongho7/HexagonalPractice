package com.sh.hexagonal.refund.adapter.out.sh;

import com.sh.hexagonal.common.exception.BaseException;
import com.sh.hexagonal.common.response.ErrorCode;

public final class ShException extends BaseException {
    public ShException(String message) {
        super(message, ErrorCode.AUTH_INVALID_TOKEN);
    }
}
