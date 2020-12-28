package com.hs.yourfit.domain.user.exception;

import com.hs.yourfit.core.error.enums.ErrorCode;
import com.hs.yourfit.core.error.exception.BusinessLogicException;

public class BusinessInfoNotFoundException extends BusinessLogicException {
    public BusinessInfoNotFoundException(){super(ErrorCode.BUSINESS_INFO_NOT_FOUND);}
}
