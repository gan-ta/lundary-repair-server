package com.hs.yourfit.domain.user.service.Impl;

import com.hs.yourfit.common.annotation.ServiceTest;
import com.hs.yourfit.common.before.UserSetUp;
import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserAddress;
import com.hs.yourfit.domain.user.entity.UserBusinessInfo;
import com.hs.yourfit.domain.user.repository.UserAddressRepository;
import com.hs.yourfit.domain.user.repository.UserBusinessInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ServiceTest
class UserDeleteServiceImplTest {

    @InjectMocks
    private UserDeleteServiceImpl userDeleteService;

    @Mock
    private JwtCore jwtCore;

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private UserBusinessInfoRepository userBusinessInfoRepository;

    private UserSetUp userSetUP = new UserSetUp();

    @Test
    @DisplayName("회원 주소정보 삭제")
    void deleteUserAddr(){
        //given
        User user = userSetUP.createCustomerUserWithId();
        UserAddress userAddress = userSetUP.createNormalUserAddress(user);
        given(jwtCore.findUserByToken(any(String.class))).willReturn(user);
        given(userAddressRepository.existsByUserAndId(any(User.class),any(Long.class))).willReturn(true);
        given(userAddressRepository.findById(any(Long.class))).willReturn(Optional.of(userAddress));

        //when
        userDeleteService.deleteUserAddr(1L,"token");

        //then
        assertTrue(userAddress.getDeleted());
    }

    @Test
    @DisplayName("고객 회원정보 삭제")
    void deleteCustomerUser(){
        //given
        User user = userSetUP.createCustomerUserWithId();
        UserAddress userAddress = userSetUP.createRepresentUserAddress(user);
        List<UserAddress> userAddressList = new ArrayList<>();
        userAddressList.add(userAddress);
        given(jwtCore.findUserByToken(any(String.class))).willReturn(user);
        given(userAddressRepository.findByUser(any(User.class))).willReturn(userAddressList);

        //when
        userDeleteService.deleteCustomerUser("token");

        //then
        assertTrue(user.getDeleted());
        assertTrue(userAddress.getDeleted());
    }

    @Test
    @DisplayName("판매자 회원정보 삭제")
    void deleteSellerUser(){
        //given
        User user = userSetUP.createSellerUser();
        UserAddress userAddress = userSetUP.createRepresentUserAddress(user);
        UserBusinessInfo userBusinessInfo = userSetUP.createBusinessInfo(user);
        List<UserAddress> userAddressList = new ArrayList<>();
        userAddressList.add(userAddress);
        given(jwtCore.findUserByToken(any(String.class))).willReturn(user);
        given(userBusinessInfoRepository.findByUser(any(User.class))).willReturn(Optional.of(userBusinessInfo));
        given(userAddressRepository.findByUser(any(User.class))).willReturn(userAddressList);

        //when
        userDeleteService.deleteSellerUser("token");

        //then
        assertTrue(user.getDeleted());
        assertTrue(userAddress.getDeleted());
        assertTrue(userBusinessInfo.getDeleted());
    }
}
