package com.hs.lunpair.domain.user.dto.request;

import com.hs.lunpair.common.validator.EnumValid;
import com.hs.lunpair.domain.user.entity.UserGender;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    private String homePhoneNumber;

    @NotBlank(message = "휴대폰 번호를 입력해주세요")
    private String cellPhoneNumber;

    @NotBlank(message = "성별을 선택해주세요")
    @EnumValid(enumClass = UserGender.class, message = "성별을 다시 한번 확인해주세요")
    private UserGender userGender;
}
