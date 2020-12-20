package com.hs.lunpair.core.security.jwt;

import com.hs.lunpair.core.error.exception.UserDefineException;

public class JwtTokenInvalidException extends UserDefineException {
    public JwtTokenInvalidException(String message) {
        super(message);
    }
}
