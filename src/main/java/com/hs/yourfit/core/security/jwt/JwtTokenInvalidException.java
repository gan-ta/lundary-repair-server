package com.hs.yourfit.core.security.jwt;

import com.hs.yourfit.core.error.exception.UserDefineException;

public class JwtTokenInvalidException extends UserDefineException {
    public JwtTokenInvalidException(String message) {
        super(message);
    }
}
