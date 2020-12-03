package com.hs.lunpair.core.security.jwt;

import com.hs.lunpair.core.error.exception.BusinessLogicException;
import com.hs.lunpair.core.error.dto.ErrorCode;

public class JwtTokenExpiredException extends BusinessLogicException {
    public JwtTokenExpiredException() {
        super(ErrorCode.JWT_TOKEN_EXPIRED);
    }
}
