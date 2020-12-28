package com.hs.yourfit.domain.dress.dto.request;

import com.hs.yourfit.domain.dress.entity.Pants;
import com.hs.yourfit.domain.dress.entity.enums.LengthCategory;
import com.hs.yourfit.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Getter
public class PantsCreateRequest {

    @NotBlank(message = "카테고리를 입력해주세요")
    private String lengthCategory;

    @NotBlank(message = "총장을 입력해주세요")
    private int length;

    @NotBlank(message = "허리단면을 입력해주세요")
    private int waist;

    @NotBlank(message = "허벅지단면을 입력해주세요")
    private int thigh;

    @NotBlank(message = "밑위를 입력해주세요")
    private int crotch;

    @NotBlank(message = "밑단단면을 입력해주세요")
    private int tailEdge;

    public Pants of(User user){
        return Pants.builder()
                .user(user)
                .lengthCategory(LengthCategory.findByString(this.lengthCategory))
                .length(this.length)
                .waist(this.waist)
                .thigh(this.thigh)
                .crotch(this.crotch)
                .tailEdge(this.tailEdge)
                .build();
    }
}
