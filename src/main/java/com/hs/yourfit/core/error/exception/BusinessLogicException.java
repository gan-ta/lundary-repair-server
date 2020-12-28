package com.hs.yourfit.core.error.exception;

import com.hs.yourfit.core.error.enums.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException{
    private ErrorCode errorCode;

    public BusinessLogicException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessLogicException(String part, ErrorCode errorCode){
        super(part + " " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
