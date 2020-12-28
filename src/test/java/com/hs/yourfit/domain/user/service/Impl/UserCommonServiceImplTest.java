package com.hs.yourfit.domain.user.service.Impl;

import com.hs.yourfit.common.annotation.ServiceTest;
import com.hs.yourfit.common.before.UserDtoSetUp;
import com.hs.yourfit.common.before.UserSetUp;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserPhone;
import com.hs.yourfit.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ServiceTest
class UserCommonServiceImplTest {

    @InjectMocks
    private UserCommonServiceImpl userCommonService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserSetUp userSetUp = new UserSetUp();
    private UserDtoSetUp userDtoSetUp = new UserDtoSetUp();

    @Test
    @DisplayName("유저 이메일 찾기")
    void findUserEmailByPhoneNumber1(){
        //given
        User user = userSetUp.createAdminUser();
        given(userRepository.findByNameAndPhoneNumber1(any(String.class),any(UserPhone.class))).willReturn(Optional.of(user));

        //when
        String result = userCommonService.findUserEmailByPhoneNumber1("name","010-0000-0000");

        //then
        assertEquals(user.getEmail(),result);
    }

    @Test
    @DisplayName("유저 비밀번호 변경")
    void changeUserPassword(){
        //given
        User user = userSetUp.createAdminUser();
        given(userRepository.findByEmail(any(String.class))).willReturn(Optional.of(user));
        given(passwordEncoder.encode(any(String.class))).willReturn("12345");

        //when
        String message = userCommonService.changeUserPassword(userDtoSetUp.updateUserPwRequest(user));

        //then
        assertEquals("success",message);
    }
}