package com.hs.yourfit.domain.user.service.Impl;

import com.hs.yourfit.common.annotation.ServiceTest;
import com.hs.yourfit.common.before.UserDtoSetUp;
import com.hs.yourfit.common.before.UserSetUp;
import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.user.dto.request.UserBusinessInfoCreateRequest;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserBusinessInfo;
import com.hs.yourfit.domain.user.repository.UserBusinessInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ServiceTest
class UserBusinessInfoCreateServiceImplTest {

    @InjectMocks
    private UserBusinessInfoCreateServiceImpl userBusinessInfoCreateService;

    @Mock
    private JwtCore jwtCore;

    @Mock
    private UserBusinessInfoRepository userBusinessInfoRepository;

    private UserSetUp userSetUp = new UserSetUp();
    private UserDtoSetUp userDtoSetUp = new UserDtoSetUp();

    @Test
    @DisplayName("사업자 정보 저장")
    void createUserBusinessInfo(){
        //given
        User user = userSetUp.createSellerUser();
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);
        UserBusinessInfoCreateRequest userBusinessInfoCreateRequest = userDtoSetUp.createBusinessInfoCreateRequest();
        given(userBusinessInfoRepository.save(any(UserBusinessInfo.class))).willReturn(userBusinessInfo);

        //when
        String message = userBusinessInfoCreateService.createUserBusinessInfo(userBusinessInfoCreateRequest,"token");

        //then
        assertEquals("success", message);
    }

}