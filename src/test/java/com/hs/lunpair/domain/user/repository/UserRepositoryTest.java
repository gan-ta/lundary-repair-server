package com.hs.lunpair.domain.user.repository;

import com.hs.lunpair.common.annotation.RepositoryTest;
import com.hs.lunpair.common.before.UserSetUp;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    UserSetUp userSetUp = new UserSetUp();

    @Test
    @DisplayName("이메일로 사용자 찾기")
    public void findUserByEmail(){
        //given
        User user = userSetUp.createCustomerUser();
        userRepository.save(user);

        //when
        User result = userRepository.findByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new);

        //then
        assertEquals(user.getId(), result.getId());
    }

    @Test
    @DisplayName("이름과 핸드폰 번호로 유저 찾기")
    public void findByNameAndPhoneNumber1(){
        //given
        User user = userSetUp.createCustomerUser();
        userRepository.save(user);

        //when
        User result = userRepository.findByNameAndPhoneNumber1(user.getName(), user.getPhoneNumber1())
                .orElseThrow(UserNotFoundException::new);

        //then
        assertEquals(user.getId(), result.getId());
    }

    @Test
    @DisplayName("유저 저장하기")
    public void saveUser(){
        //given
        User user = userSetUp.createSellerUser();

        //when
        User result = userRepository.save(user);

        //then
        assertEquals(user.getId(), result.getId());
    }
}