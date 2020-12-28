package com.hs.yourfit.domain.dress.dto.response;

import com.hs.yourfit.common.model.ResponseData;
import com.hs.yourfit.domain.dress.entity.Top;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TopGetResponse extends ResponseData {

    List<Response> response;

    @Getter
    @Builder
    private static class Response{
        private Long topId;
        private String lengthCategory;
        private int length;
        private int shoulder;
        private int chest;
        private int retail;

        private static Response toDto(Top top){
            return Response.builder()
                    .topId(top.getId())
                    .lengthCategory(top.getLengthCategory().getCategory())
                    .length(top.getLength())
                    .shoulder(top.getShoulder())
                    .chest(top.getChest())
                    .retail(top.getRetail())
                    .build();
        }
    }

    @Builder
    public TopGetResponse(List<Top> topList, HttpStatus httpStatus){
        super(true,httpStatus);
        this.response = topList.stream().map(Response::toDto).collect(Collectors.toList());
    }

}
