package com.hs.yourfit.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class StringResponse extends ResponseData{
    Response response = new Response();

    @Builder
    public StringResponse(String message, HttpStatus status){
        super(true, status);
        this.response.data = message;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Response{
        String data;
    }
}
