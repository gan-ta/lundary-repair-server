package com.hs.lunpair.domain.user.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "필수 전화번호를 입력해주세요")
    private String phoneNumber1;

    private String phoneNumber2;
}
