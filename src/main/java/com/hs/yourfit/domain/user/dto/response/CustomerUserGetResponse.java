package com.hs.yourfit.domain.user.dto.response;

import com.hs.yourfit.common.model.ResponseData;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserAddress;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomerUserGetResponse extends ResponseData {
    Response response = new Response();

    @Builder
    public CustomerUserGetResponse(User user, List<UserAddress> userAddressList, HttpStatus httpStatus){
        super(true,httpStatus);
        this.response.email = user.getEmail();
        this.response.name = user.getName();
        this.response.phoneNumber1 = user.getPhoneNumber1().getNumber();
        this.response.phoneNumber1 = user.getPhoneNumber2().getNumber();
        this.response.userGender = user.getUserGender();
        this.response.userRole = user.getUserRole();
        this.response.userAddressInfoList = userAddressList.stream()
                .map(UserAddressInfo::toDto)
                .collect(Collectors.toList());
    }

    @Builder
    private static class UserAddressInfo{
        private Long id;
        private String baseAddr;
        private String detailAddr;
        private String zipCode;

        public static UserAddressInfo toDto(UserAddress userAddress){
            return UserAddressInfo.builder()
                    .id(userAddress.getId())
                    .baseAddr(userAddress.getBaseAddr())
                    .detailAddr(userAddress.getDetailAddr())
                    .zipCode(userAddress.getZipCode())
                    .build();
        }
    }

    @Getter
    private static class Response extends UserGetResponse{
        List<UserAddressInfo> userAddressInfoList;
    }
}
