package com.hs.lunpair.domain.user.exception;

import com.hs.lunpair.core.error.enums.ErrorCode;
import com.hs.lunpair.core.error.exception.BusinessLogicException;

public class AddressNotFoundException extends BusinessLogicException {
    public AddressNotFoundException(){super(ErrorCode.ADDRESS_NOT_FOUND);}
}
