package com.hs.lunpair.domain.user.dto.request;

import com.hs.lunpair.domain.user.entity.enums.UserGender;
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

    @NotBlank(message = "성별을 선택해주세요")
    private UserGender userGender;
}
