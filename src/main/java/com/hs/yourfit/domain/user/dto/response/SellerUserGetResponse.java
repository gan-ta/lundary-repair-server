package com.hs.yourfit.domain.user.dto.response;

import com.hs.yourfit.common.model.ResponseData;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserBusinessInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
public class SellerUserGetResponse extends ResponseData {

    Response response = new Response();

    @Getter
    private static class Response extends UserGetResponse{
        private String corporationName;
        private String representationName;
        private LocalDate openDate;
        private String registrationNumber;
    }

    @Builder
    public SellerUserGetResponse(User user, UserBusinessInfo userBusinessInfo, HttpStatus httpStatus){
        super(true,httpStatus);
        this.response.email = user.getEmail();
        this.response.name = user.getName();
        this.response.phoneNumber1 = user.getPhoneNumber1().getNumber();
        this.response.phoneNumber1 = user.getPhoneNumber2().getNumber();
        this.response.userGender = user.getUserGender();
        this.response.userRole = user.getUserRole();
        this.response.corporationName = userBusinessInfo.getCorporationName();
        this.response.representationName = userBusinessInfo.getRepresentationName();
        this.response.openDate = userBusinessInfo.getOpenDate();
        this.response.registrationNumber= userBusinessInfo.getRegistrationNumber();
    }
}
