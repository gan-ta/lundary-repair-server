package com.hs.yourfit.domain.dress.dto.request;

import com.hs.yourfit.domain.dress.entity.Top;
import com.hs.yourfit.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Getter
public class TopCreateRequest {

    @NotBlank(message = "카테고리를 입력해주세요")
    private String lengthCategory;

    @NotBlank(message = "총장을 입력해주세요")
    private int length;

    @NotBlank(message = "어깨너비를 입력해주세요")
    private int shoulder;

    @NotBlank(message = "가슴단면을 입력해주세요")
    private int chest;

    @NotBlank(message = "소매길이를 입력해주세요")
    private int retail;

    public Top of(User user){
        return Top.builder()
                .user(user)
                .length(this.length)
                .shoulder(this.shoulder)
                .chest(this.chest)
                .retail(this.retail)
                .build();
    }
}
