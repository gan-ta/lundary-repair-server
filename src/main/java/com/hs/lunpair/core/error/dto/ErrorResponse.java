package com.hs.lunpair.core.error.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int status;
    private String message;

    //에러코드와 에러 메세지를 반환하는 객체
    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }

    //에러 메세지와 400상태를 반환
    public static ErrorResponse of(String errorMessage){
        return ErrorResponse.builder()
                .status(400)
                .message(errorMessage)
                .build();
    }
}
