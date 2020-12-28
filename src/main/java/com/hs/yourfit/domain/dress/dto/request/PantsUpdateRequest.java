package com.hs.yourfit.domain.dress.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Builder
@Getter
public class PantsUpdateRequest {

    @NotEmpty(message = "하의 수선정보 아이디를 입력해주세요")
    private Long pantsId;

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
}
