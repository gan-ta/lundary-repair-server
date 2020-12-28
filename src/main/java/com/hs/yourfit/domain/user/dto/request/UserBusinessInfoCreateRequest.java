package com.hs.yourfit.domain.user.dto.request;

import com.hs.yourfit.domain.user.entity.UserBusinessInfo;
import com.hs.yourfit.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@Builder
public class UserBusinessInfoCreateRequest {

    @NotBlank(message = "법인명을 입력해 주세요")
    private String corporationName;

    @NotBlank(message = "대표명을 입력해주세요")
    private String representationName;

    private String openDate;

    @NotBlank(message = "법인 등록번호를 입력해주세요")
    private String registrationNumber;

    public UserBusinessInfo of(User user){
        return UserBusinessInfo.builder()
                .user(user)
                .corporationName(this.corporationName)
                .representationName(this.representationName)
                .openDate(LocalDate.parse(this.openDate, DateTimeFormatter.ISO_DATE))
                .registrationNumber(this.registrationNumber)
                .build();
    }
}
