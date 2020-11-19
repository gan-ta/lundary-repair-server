package com.hs.lunpair.core.error.dto;

import lombok.Getter;

@Getter
public class ErrorCode {

    private String message;
    private int status;

    ErrorCode(String message, int status){
        this.message = message;
        this.status = status;
    }
}
