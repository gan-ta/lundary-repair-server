package com.hs.yourfit.domain.user.dto.request;

import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserPhone;
import com.hs.yourfit.domain.user.entity.enums.UserGender;
import com.hs.yourfit.domain.user.entity.enums.UserRole;
import com.hs.yourfit.common.validator.Enum;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Getter
public class UserCreateRequest {

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "필수 전화번호를 입력해주세요")
    private String phoneNumber1;

    private String phoneNumber2;

    @Enum(enumClass = UserGender.class, ignoreCase = true,message = "정확한 성별을 입력해주세요")
    private String userGender;

    @Enum(enumClass = UserRole.class, ignoreCase = true,message = "정확한 역할을 확인해주세요")
    private String userRole;

    public User of(String encodePassword){
        return User.builder()
                .email(this.email)
                .password(encodePassword)
                .name(this.name)
                .phoneNumber1(new UserPhone(this.phoneNumber1))
                .phoneNumber2(new UserPhone(this.phoneNumber2))
                .userGender(UserGender.findByString(this.userGender))
                .userRole(UserRole.findByString(this.userRole))
                .build();
    }
}
