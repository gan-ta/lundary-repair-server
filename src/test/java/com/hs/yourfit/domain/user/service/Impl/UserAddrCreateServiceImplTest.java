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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ServiceTest
class UserAddrCreateServiceImplTest {

    @InjectMocks
    private UserAddrCreateServiceImpl userAddrCreateService;

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private JwtCore jwtCore;

    private UserSetUp userSetUp = new UserSetUp();
    private UserDtoSetUp userDtoSetUp = new UserDtoSetUp();

    @Test
    @DisplayName("유저 주소정보 저장")
    void createUserAddr(){
        //given
        User user = userSetUp.createSellerUser();
        UserAddress userAddress = userSetUp.createRepresentUserAddress(user);
        given(jwtCore.findUserByToken(any(String.class))).willReturn(user);
        given(userAddressRepository.existsByUser(any(User.class))).willReturn(false);
        given(userAddressRepository.save(any(UserAddress.class))).willReturn(userAddress);


        //when
        String message = userAddrCreateService.createUserAddr(userDtoSetUp.createAddrCreateRequest(),"token");

        //then
        assertEquals("success",message);
    }
}