package com.hs.yourfit.domain.dress.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Builder
@Getter
public class TopUpdateRequest {

    @NotEmpty(message = "상의 수선정보 아이디를 입력해주세요")
    private Long topId;

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
}
