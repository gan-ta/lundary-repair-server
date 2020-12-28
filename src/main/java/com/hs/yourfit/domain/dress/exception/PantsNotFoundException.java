package com.hs.yourfit.domain.dress.exception;

import com.hs.yourfit.core.error.enums.ErrorCode;
import com.hs.yourfit.core.error.exception.BusinessLogicException;

public class PantsNotFoundException extends BusinessLogicException {
    public PantsNotFoundException(){super(ErrorCode.PANTS_NOT_FOUND);}
}
