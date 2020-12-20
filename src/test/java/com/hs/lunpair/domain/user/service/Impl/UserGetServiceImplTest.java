package com.hs.lunpair.domain.user.service.Impl;

import com.hs.lunpair.common.annotation.ServiceTest;
import com.hs.lunpair.common.before.UserDtoSetUp;
import com.hs.lunpair.common.before.UserSetUp;
import com.hs.lunpair.core.security.jwt.JwtCore;
import com.hs.lunpair.domain.user.dto.response.CustomerUserGetResponse;
import com.hs.lunpair.domain.user.dto.response.SellerUserGetResponse;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.entity.UserAddress;
import com.hs.lunpair.domain.user.entity.UserBusinessInfo;
import com.hs.lunpair.domain.user.repository.UserAddressRepository;
import com.hs.lunpair.domain.user.repository.UserBusinessInfoRepository;
import com.hs.lunpair.domain.user.service.UserGetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ServiceTest
class UserGetServiceImplTest {

    @InjectMocks
    private UserGetServiceImpl userGetService;

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private UserBusinessInfoRepository userBusinessInfoRepository;

    @Mock
    private JwtCore jwtCore;

    UserSetUp userSetUp = new UserSetUp();

    @Test
    @DisplayName("일반 회원정보 가져오기")
    void getCustomerUser(){
        //given
        User user = userSetUp.createCustomerUserWithId();
        UserAddress userAddress1 = userSetUp.createRepresentUserAddress(user);
        UserAddress userAddress2 = userSetUp.createNormalUserAddress(user);
        List<UserAddress> userAddressList = new ArrayList<>();
        userAddressList.add(userAddress1);
        userAddressList.add(userAddress2);
        given(jwtCore.findUserByToken(any(String.class))).willReturn(user);
        given(userAddressRepository.findByUser(any(User.class))).willReturn(userAddressList);

        //when
        CustomerUserGetResponse result = userGetService.getCustomerUser("token", HttpStatus.OK);

        //then
        assertEquals(HttpStatus.OK.value(),result.getStatus());
    }

    @Test
    @DisplayName("판매자 회원정보 가져오기")
    void getSellerUser(){
        //given
        User user = userSetUp.createSellerUser();
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);
        given(jwtCore.findUserByToken(any(String.class))).willReturn(user);
        given(userBusinessInfoRepository.findByUser(any(User.class))).willReturn(Optional.of(userBusinessInfo));

        //when
        SellerUserGetResponse result = userGetService.getSellerUser("token", HttpStatus.OK);

        //then
        assertEquals(HttpStatus.OK.value(),result.getStatus());
    }
}