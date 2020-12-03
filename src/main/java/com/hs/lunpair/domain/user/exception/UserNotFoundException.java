package com.hs.lunpair.domain.user.exception;

import com.hs.lunpair.core.error.exception.BusinessLogicException;
import com.hs.lunpair.core.error.dto.ErrorCode;

public class UserNotFoundException extends BusinessLogicException {
    public UserNotFoundException(){super(ErrorCode.USER_NOT_FOUND);}
}
