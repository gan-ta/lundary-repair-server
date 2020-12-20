package com.hs.lunpair.domain.user.repository;

import com.hs.lunpair.common.annotation.RepositoryTest;
import com.hs.lunpair.common.before.UserSetUp;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.entity.UserAddress;
import com.hs.lunpair.domain.user.exception.AddressNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
class UserAddressRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAddressRepository userAddressRepository;

    UserSetUp userSetUp = new UserSetUp();

    @Test
    @DisplayName("유저 주소지 저장")
    public void saveUserAddr(){
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userAddress = userSetUp.createRepresentUserAddress(user);

        //when
        userRepository.save(user);
        UserAddress result = userAddressRepository.save(userAddress);

        //then
        assertEquals(userAddress.getId(), result.getId());
    }

    @Test
    @DisplayName("유저의 주소지 목록 찾기")
    public void findByUser(){
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userRepresentAddress = userSetUp.createRepresentUserAddress(user);
        UserAddress userNormalAddress = userSetUp.createNormalUserAddress(user);

        //when
        userRepository.save(user);
        userAddressRepository.save(userRepresentAddress);
        userAddressRepository.save(userNormalAddress);
        List<UserAddress> result = userAddressRepository.findByUser(user);

        //then
        assertEquals(2,result.size());
    }

    @Test
    @DisplayName("유저 주소 유무 확인")
    public void existsByUser(){
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userRepresentAddress = userSetUp.createRepresentUserAddress(user);

        //when
        userRepository.save(user);
        userAddressRepository.save(userRepresentAddress);
        boolean result = userAddressRepository.existsByUser(user);

        //then
        assertEquals(true, result);
    }

    @Test
    @DisplayName("유저에 대한 특정 주소정보 유무 확인")
    public void existsByUserAndId(){
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userRepresentAddress = userSetUp.createRepresentUserAddress(user);

        //when
        userRepository.save(user);
        userAddressRepository.save(userRepresentAddress);
        boolean result = userAddressRepository.existsByUserAndId(user,userRepresentAddress.getId());

        //then
        assertEquals(true, result);
    }

    @Test
    @DisplayName("주소 아이디로 주소 정보 가져오기")
    public void findById(){
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userRepresentAddress = userSetUp.createRepresentUserAddress(user);

        //when
        userRepository.save(user);
        userAddressRepository.save(userRepresentAddress);
        UserAddress result = userAddressRepository
                .findById(userRepresentAddress.getId()).orElseThrow(AddressNotFoundException::new);

        //then
        assertEquals(userRepresentAddress.getId(),result.getId());
    }

    @Test
    @DisplayName("유저의 대표 주소 목록 가져오기")
    public void findByUserAndRepresent(){
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userRepresentAddress = userSetUp.createRepresentUserAddress(user);

        //when
        userRepository.save(user);
        userAddressRepository.save(userRepresentAddress);
        List<UserAddress> result =  userAddressRepository.findByUserAndRepresent(user,true);

        //then
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("유저의 읿나 주소 목록 가져오기")
    public void findByUserAndNormal(){
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userNormalAddress = userSetUp.createNormalUserAddress(user);

        //when
        userRepository.save(user);
        userAddressRepository.save(userNormalAddress);
        List<UserAddress> result =  userAddressRepository.findByUserAndRepresent(user,false);

        //then
        assertEquals(1, result.size());
    }
}