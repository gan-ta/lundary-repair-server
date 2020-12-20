package com.hs.lunpair.domain.user.repository;

import com.hs.lunpair.common.annotation.RepositoryTest;
import com.hs.lunpair.common.before.UserSetUp;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.entity.UserBusinessInfo;
import com.hs.lunpair.domain.user.exception.BusinessInfoNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
class UserBusinessInfoRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBusinessInfoRepository userBusinessInfoRepository;

    UserSetUp userSetUp = new UserSetUp();

    @Test
    @DisplayName("사업자 정보 저장")
    public void saveUserBusinessInfo(){
        //given
        User user = userSetUp.createCustomerUser();
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);

        //when
        userRepository.save(user);
        UserBusinessInfo result = userBusinessInfoRepository.save(userBusinessInfo);


        //then
        assertEquals(userBusinessInfo.getId(), result.getId());
    }

    @Test
    @DisplayName("사업자 정보 찾기")
    public void findByUser(){
        //given
        User user = userSetUp.createCustomerUser();
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);

        //when
        userRepository.save(user);
        userBusinessInfoRepository.save(userBusinessInfo);
        UserBusinessInfo result = userBusinessInfoRepository.findByUser(user).orElseThrow(BusinessInfoNotFoundException::new);

        //then
        assertEquals(userBusinessInfo.getId(),result.getId());
    }

}