package com.hs.yourfit.domain.user.exception;

import com.hs.yourfit.core.error.enums.ErrorCode;
import com.hs.yourfit.core.error.exception.BusinessLogicException;

public class AddressNotFoundException extends BusinessLogicException {
    public AddressNotFoundException(){super(ErrorCode.ADDRESS_NOT_FOUND);}
}
