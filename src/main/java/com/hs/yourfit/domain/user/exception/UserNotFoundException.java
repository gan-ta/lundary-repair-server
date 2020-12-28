package com.hs.yourfit.domain.user.exception;

import com.hs.yourfit.core.error.exception.BusinessLogicException;
import com.hs.yourfit.core.error.enums.ErrorCode;

public class UserNotFoundException extends BusinessLogicException {
    public UserNotFoundException(){super(ErrorCode.USER_NOT_FOUND);}
}
