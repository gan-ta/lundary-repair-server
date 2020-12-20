package com.hs.lunpair.domain.user.exception;

import com.hs.lunpair.core.error.enums.ErrorCode;
import com.hs.lunpair.core.error.exception.BusinessLogicException;

public class BusinessInfoNotFoundException extends BusinessLogicException {
    public BusinessInfoNotFoundException(){super(ErrorCode.BUSINESS_INFO_NOT_FOUND);}
}
