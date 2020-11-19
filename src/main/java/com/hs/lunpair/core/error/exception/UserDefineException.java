package com.hs.lunpair.core.error.exception;

import lombok.Getter;

@Getter
public class UserDefineException extends RuntimeException {
    private String originalErrorMessage;

}
