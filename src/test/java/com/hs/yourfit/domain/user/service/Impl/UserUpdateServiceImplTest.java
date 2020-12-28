package com.hs.yourfit.domain.user.service.Impl;

import com.hs.yourfit.common.annotation.ServiceTest;
import com.hs.yourfit.common.before.UserDtoSetUp;
import com.hs.yourfit.common.before.UserSetUp;
import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserAddress;
import com.hs.yourfit.domain.user.repository.UserAddressRepository;
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
class UserUpdateServiceImplTest {

    @InjectMocks
    private UserUpdateServiceImpl userUpdateService;

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private JwtCore jwtCore;

    private UserSetUp userSetUp = new UserSetUp();
    private UserDtoSetUp userDtoSetUp = new UserDtoSetUp();

    @Test
    @DisplayName("유저 기본정보 변경")
    void updateUserCommonInfo(){
        //given
        User user = userSetUp.createCustomerUserWithId();
        given(jwtCore.findUserByToken(any(String.class))).willReturn(user);

        //when
        String message = userUpdateService.updateUserCommonInfo(userDtoSetUp.updateUserRequest(),"token");

        //then
        assertEquals("success",message);
    }

    @Test
    @DisplayName("유저 대표 주소지 변경")
    void changeRepresentAddr(){
        //given
        User user = userSetUp.createCustomerUserWithId();
        UserAddress userRepresentAddress = userSetUp.createRepresentUserAddress(user);
        UserAddress userNormalAddress = userSetUp.createNormalUserAddress(user);
        List<UserAddress> userAddressList = new ArrayList<>();
        userAddressList.add(userRepresentAddress);
        userAddressList.add(userNormalAddress);
        given(jwtCore.findUserByToken(any(String.class))).willReturn(user);
        given(userAddressRepository.existsByUserAndId(any(User.class),any(Long.class))).willReturn(true);
        given(userAddressRepository.findByUserAndRepresent(any(User.class),any(Boolean.class))).willReturn(userAddressList);
        given(userAddressRepository.findById(any(Long.class))).willReturn(Optional.of(userNormalAddress));

        //when
        userUpdateService.changeRepresentAddr(1L,"token");

        //then
        assertTrue(userNormalAddress.isRepresent());
        assertFalse(userRepresentAddress.isRepresent());
    }
}