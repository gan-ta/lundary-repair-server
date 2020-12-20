package com.hs.lunpair.common.before;

import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.entity.UserAddress;
import com.hs.lunpair.domain.user.entity.UserBusinessInfo;
import com.hs.lunpair.domain.user.entity.UserPhone;
import com.hs.lunpair.domain.user.entity.enums.UserGender;
import com.hs.lunpair.domain.user.entity.enums.UserRole;

import java.time.LocalDate;

public class UserSetUp {

    public User createCustomerUser(){
        return User.builder()
                .email("test@naver.com")
                .password("1234")
                .name("customer")
                .phoneNumber1(new UserPhone("010-1111-2222"))
                .phoneNumber2(new UserPhone("010-4444-5555"))
                .userGender(UserGender.MALE)
                .userRole(UserRole.CUSTOMER)
                .build();
    }

    public User createCustomerUserWithId(){
        return User.builder()
                .id(1L)
                .email("test@naver.com")
                .password("1234")
                .name("customer")
                .phoneNumber1(new UserPhone("010-1111-2222"))
                .phoneNumber2(new UserPhone("010-4444-5555"))
                .userGender(UserGender.MALE)
                .userRole(UserRole.CUSTOMER)
                .build();
    }

    public User createAdminUser(){
        return User.builder()
                .email("test@naver.com")
                .password("1234")
                .name("admin")
                .phoneNumber1(new UserPhone("010-1111-2222"))
                .phoneNumber2(new UserPhone("010-4444-5555"))
                .userGender(UserGender.MALE)
                .userRole(UserRole.ADMIN)
                .build();
    }

    public User createSellerUser(){
        return User.builder()
                .email("test@naver.com")
                .password("1234")
                .name("seller")
                .phoneNumber1(new UserPhone("010-1111-2222"))
                .phoneNumber2(new UserPhone("010-4444-5555"))
                .userGender(UserGender.MALE)
                .userRole(UserRole.SELLER)
                .build();
    }

    public UserAddress createRepresentUserAddress(User user){
        return UserAddress.builder()
                .baseAddr("baseAddr")
                .detailAddr("detailAddr")
                .zipCode("12345")
                .represent(true)
                .user(user)
                .build();
    }

    public UserAddress createNormalUserAddress(User user) {
        return UserAddress.builder()
                .baseAddr("baseAddr")
                .detailAddr("detailAddr")
                .zipCode("12345")
                .represent(false)
                .user(user)
                .build();
    }

    public UserBusinessInfo createBusinessInfo(User user){
        return UserBusinessInfo.builder()
                .corporationName("법인명")
                .representationName("대표자이름")
                .openDate(LocalDate.now())
                .registrationNumber("11234-12-512353")
                .user(user)
                .build();
    }
}
