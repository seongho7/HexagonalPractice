package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.common.response.ErrorCode;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationProcessingException extends AuthenticationException {
    private ErrorCode errorCode;
    public AuthenticationProcessingException(ErrorCode errorCode, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
