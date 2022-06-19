package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.common.response.ErrorCode;

public class TokenExpiredException extends AuthenticationProcessingException {

    public TokenExpiredException() {
        super(ErrorCode.AUTH_TOKEN_EXPIRED, null, null);
    }
}
