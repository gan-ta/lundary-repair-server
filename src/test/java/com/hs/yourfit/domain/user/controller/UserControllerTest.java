package com.hs.yourfit.domain.user.controller;

import com.hs.yourfit.common.annotation.IntegrationTest;
import com.hs.yourfit.domain.user.dto.request.*;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserAddress;
import com.hs.yourfit.domain.user.entity.UserBusinessInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends IntegrationTest {

    @Test
    @DisplayName("회원가입")
    void createUser() throws Exception {
        //given
        UserCreateRequest dto = userDtoSetUp.createUserRequest();

        //when
        mockMvc.perform(
                post(USER_URL + "/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(dto)
                        )
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("일반회원 주소등록")
    void createUserAddr() throws Exception {
        //given
        User user = userSetUp.createCustomerUser();
        userRepository.save(user);
        UserAddrCreateRequest dto = userDtoSetUp.createAddrCreateRequest();


        //when
        mockMvc.perform(
                post(USER_URL + "/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(),user.getUserRole()))
                        .content(
                                objectMapper.writeValueAsString(dto)
                        )
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("판매자회원 법인정보 등록")
    void createUserBusinessInfo() throws Exception {
        //given
        User user = userSetUp.createSellerUser();
        userRepository.save(user);
        UserBusinessInfoCreateRequest dto = userDtoSetUp.createBusinessInfoCreateRequest();


        //when
        mockMvc.perform(
                post(USER_URL + "/businessInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(),user.getUserRole()))
                        .content(
                                objectMapper.writeValueAsString(dto)
                        )
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("일반 회원정보 가져오기")
    void getCustomerUser() throws Exception{
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userAddress = userSetUp.createRepresentUserAddress(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress);

        //when
        MvcResult mvcResult = mockMvc.perform(
                get(USER_URL + "/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(), user.getUserRole()))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("판매자 회원정보 가져오기")
    void getSellerUser() throws Exception{
        //given
        User user = userSetUp.createSellerUser();
        UserAddress userAddress = userSetUp.createRepresentUserAddress(user);
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress);
        userBusinessInfoRepository.save(userBusinessInfo);

        //when
        MvcResult mvcResult = mockMvc.perform(
                get(USER_URL + "/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(), user.getUserRole()))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("아이디 찾기")
    void getUserIdentity() throws Exception{
        //given
        User user = userSetUp.createSellerUser();
        UserAddress userAddress = userSetUp.createRepresentUserAddress(user);
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress);
        userBusinessInfoRepository.save(userBusinessInfo);

        //when
        MvcResult mvcResult = mockMvc.perform(
                get(USER_URL + "/identity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userName",user.getName())
                        .param("phoneNumber",user.getPhoneNumber1().getNumber())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("비밀번호 변경하기")
    void patchUserPw() throws Exception{
        //given
        User user = userSetUp.createSellerUser();
        UserAddress userAddress = userSetUp.createRepresentUserAddress(user);
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress);
        userBusinessInfoRepository.save(userBusinessInfo);

        UserPwUpdateRequest dto = userDtoSetUp.updateUserPwRequest(user);

        //when
       mockMvc.perform(
                patch(USER_URL + "/pw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(dto)
                        )
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("사용자 기본정보 변경")
    void putUserIdentity() throws Exception{
        //given
        User user = userSetUp.createSellerUser();
        UserAddress userAddress = userSetUp.createRepresentUserAddress(user);
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress);
        userBusinessInfoRepository.save(userBusinessInfo);

        UserUpdateRequest dto = userDtoSetUp.updateUserRequest();

        //when
        mockMvc.perform(
                put(USER_URL + "/info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(), user.getUserRole()))
                        .content(
                                objectMapper.writeValueAsString(dto)
                        )
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("사용자 대표 주소지 변경")
    void patchUserRepresentAddr() throws Exception {
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userAddress1 = userSetUp.createRepresentUserAddress(user);
        UserAddress userAddress2 = userSetUp.createNormalUserAddress(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress1);
        userAddressRepository.save(userAddress2);

        //when
        mockMvc.perform(
                patch(USER_URL + "/represent/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(), user.getUserRole()))
                        .content(userAddress2.getId().toString())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertTrue(userAddress2.isRepresent());
    }

    @Test
    @DisplayName("사용자 주소 삭제")
    void deleteUserAddr() throws Exception {
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userAddress1 = userSetUp.createRepresentUserAddress(user);
        UserAddress userAddress2 = userSetUp.createNormalUserAddress(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress1);
        userAddressRepository.save(userAddress2);

        //when
        mockMvc.perform(
                delete(USER_URL + "/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(), user.getUserRole()))
                        .content(userAddress2.getId().toString())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertTrue(userAddress2.getDeleted());
    }

    @Test
    @DisplayName("일반 사용자 회원탈퇴")
    void deleteCustomerUser() throws Exception {
        //given
        User user = userSetUp.createCustomerUser();
        UserAddress userAddress1 = userSetUp.createRepresentUserAddress(user);
        UserAddress userAddress2 = userSetUp.createNormalUserAddress(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress1);
        userAddressRepository.save(userAddress2);

        //when
        mockMvc.perform(
                delete(USER_URL + "/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(), user.getUserRole()))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertTrue(user.getDeleted());
    }

    @Test
    @DisplayName("판매자 회원탈퇴")
    void deleteSellerUser() throws Exception {
        //given
        User user = userSetUp.createSellerUser();
        UserAddress userAddress1 = userSetUp.createRepresentUserAddress(user);
        UserAddress userAddress2 = userSetUp.createNormalUserAddress(user);
        UserBusinessInfo userBusinessInfo = userSetUp.createBusinessInfo(user);
        userRepository.save(user);
        userAddressRepository.save(userAddress1);
        userAddressRepository.save(userAddress2);
        userBusinessInfoRepository.save(userBusinessInfo);

        //when
        mockMvc.perform(
                delete(USER_URL + "/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("accessToken", jwtCore.createAccessToken(user.getEmail(), user.getUserRole()))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertTrue(user.getDeleted());
    }
}