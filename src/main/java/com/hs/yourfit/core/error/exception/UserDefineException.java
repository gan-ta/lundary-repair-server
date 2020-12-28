package com.hs.yourfit.core.error.exception;

import lombok.Getter;

//사용자 정의 예외 처리 발생
@Getter
public class UserDefineException extends RuntimeException {
    private String originalErrorMessage;

    public UserDefineException(String message){super(message);}

    public UserDefineException(String message, String originalErrorMessage){
        super(message);
        this.originalErrorMessage = originalErrorMessage;
    }
}
