package com.hs.yourfit.domain.dress.dto.response;

import com.hs.yourfit.common.model.ResponseData;
import com.hs.yourfit.domain.dress.entity.Pants;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PantsGetResponse extends ResponseData {

    List<Response> response;

    @Getter
    @Builder
    private static class Response{
        private Long pantsId;
        private String lengthCategory;
        private int length;
        private int waist;
        private int thigh;
        private int crotch;
        private int tailEdge;

        private static Response toDto(Pants pants){
            return Response.builder()
                    .pantsId(pants.getId())
                    .lengthCategory(pants.getLengthCategory().getCategory())
                    .length(pants.getLength())
                    .waist(pants.getWaist())
                    .thigh(pants.getThigh())
                    .crotch(pants.getCrotch())
                    .tailEdge(pants.getTailEdge())
                    .build();
        }
    }

    @Builder
    public PantsGetResponse(List<Pants> pantList, HttpStatus httpStatus){
        super(true,httpStatus);
        this.response = pantList.stream().map(Response::toDto).collect(Collectors.toList());
    }
}
