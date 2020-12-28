package com.hs.yourfit.core.security.jwt;

import com.hs.yourfit.core.error.exception.BusinessLogicException;
import com.hs.yourfit.core.error.enums.ErrorCode;

public class JwtTokenExpiredException extends BusinessLogicException {
    public JwtTokenExpiredException() {
        super(ErrorCode.JWT_TOKEN_EXPIRED);
    }
}
