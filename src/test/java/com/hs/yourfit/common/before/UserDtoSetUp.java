package com.hs.yourfit.common.before;

import com.hs.yourfit.domain.user.dto.request.*;
import com.hs.yourfit.domain.user.entity.User;

public class UserDtoSetUp {
    public UserAddrCreateRequest createAddrCreateRequest(){
        return UserAddrCreateRequest.builder()
                .baseAddr("baseAddr")
                .detailAddr("detailAddr")
                .zipCode("00000")
                .build();
    }

    public UserBusinessInfoCreateRequest createBusinessInfoCreateRequest(){
        return UserBusinessInfoCreateRequest.builder()
                .corporationName("법인명")
                .representationName("대표자명")
                .openDate("2020-12-01")
                .registrationNumber("등록번호")
                .build();
    }

    public UserCreateRequest createUserRequest(){
        return UserCreateRequest.builder()
                .email("test@test.com")
                .password("12345")
                .name("test")
                .phoneNumber1("010-1111-2222")
                .phoneNumber2("010-2222-3333")
                .userGender("MALE")
                .userRole("CUSTOMER")
                .build();
    }

    public UserPwUpdateRequest updateUserPwRequest(User user){
        return UserPwUpdateRequest.builder()
                .email(user.getEmail())
                .phoneNumber1(user.getPhoneNumber1().getNumber())
                .build();
    }

    public UserUpdateRequest updateUserRequest(){
        return UserUpdateRequest.builder()
                .name("test")
                .phoneNumber1("010-1111-2222")
                .phoneNumber2("010-4444-5555")
                .build();
    }
}
