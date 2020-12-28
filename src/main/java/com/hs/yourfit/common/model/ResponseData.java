package com.hs.yourfit.common.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private boolean result;
    private int status;

    public ResponseData(boolean result,HttpStatus status){
        this.result = true;
        this.status = status.value();
    }
}
