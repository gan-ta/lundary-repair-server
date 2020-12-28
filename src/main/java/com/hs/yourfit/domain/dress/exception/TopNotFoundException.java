package com.hs.yourfit.domain.dress.exception;

import com.hs.yourfit.core.error.enums.ErrorCode;
import com.hs.yourfit.core.error.exception.BusinessLogicException;

public class TopNotFoundException extends BusinessLogicException {
    public TopNotFoundException(){super(ErrorCode.TOP_NOT_FOUND);}
}
