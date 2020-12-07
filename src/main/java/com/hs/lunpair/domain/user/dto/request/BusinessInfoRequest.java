package com.hs.lunpair.domain.user.dto.request;

import com.hs.lunpair.domain.user.entity.BusinessInfo;
import com.hs.lunpair.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class BusinessInfoRequest{

    @NotBlank(message = "법인명을 입력해 주세요")
    private String corporationName;

    @NotBlank(message = "대표명을 입력해주세요")
    private String representationName;

    @NotBlank(message = "개업 일자를 입력해 주세요")
    private LocalDate openDate;

    @NotBlank(message = "법인 등록번호를 입력해주세요")
    private String registrationNumber;

    public BusinessInfo of(User user){
        return BusinessInfo.builder()
                .user(user)
                .corporationName(this.corporationName)
                .representationName(this.representationName)
                .openDate(this.openDate)
                .registrationNumber(this.registrationNumber)
                .build();
    }
}
