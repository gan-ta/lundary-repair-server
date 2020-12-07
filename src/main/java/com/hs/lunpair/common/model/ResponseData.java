package com.hs.lunpair.common.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData<T> {
    private boolean result;
    private T data;
    private int status;

    public ResponseData(T data, HttpStatus status){
        this.result = true;
        this.data = data;
        this.status = status.value();
    }
}
