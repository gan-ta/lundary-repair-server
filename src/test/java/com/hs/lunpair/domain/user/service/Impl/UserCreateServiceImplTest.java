package com.hs.lunpair.domain.user.service.Impl;

import com.hs.lunpair.common.annotation.ServiceTest;
import com.hs.lunpair.common.before.UserDtoSetUp;
import com.hs.lunpair.common.before.UserSetUp;
import com.hs.lunpair.core.security.jwt.JwtCore;
import com.hs.lunpair.domain.user.dto.request.UserCreateRequest;
import com.hs.lunpair.domain.user.dto.response.UserCreateResponse;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.entity.enums.UserRole;
import com.hs.lunpair.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

@ServiceTest
class UserCreateServiceImplTest {

    @InjectMocks
    private UserCreateServiceImpl userCreateService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtCore jwtCore;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserSetUp userSetUp = new UserSetUp();
    private UserDtoSetUp userDtoSetUp = new UserDtoSetUp();

    @Test
    @DisplayName("유저 생성")
    void createUser(){
        //given
        User user = userSetUp.createCustomerUserWithId();
        UserCreateRequest userCreateRequest = userDtoSetUp.createUserRequest();
        given(userRepository.save(any(User.class))).willReturn(user);
        given(jwtCore.createAccessToken(any(String.class),any(UserRole.class))).willReturn("token");

        //when
        String token = userCreateService.createUser(userCreateRequest);

        //then
        assertEquals("token",token);
    }

}