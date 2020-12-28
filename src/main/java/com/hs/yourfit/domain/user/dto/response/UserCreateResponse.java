package com.hs.yourfit.domain.user.dto.response;

import com.hs.yourfit.common.model.ResponseData;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
public class UserCreateResponse extends ResponseData {
    Response response = new Response();

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Response{
        private String accessToken;
    }

    @Builder
    public UserCreateResponse(String accessToken, HttpStatus status){
        super(true,status);
        this.response.accessToken = accessToken;
    }
}
