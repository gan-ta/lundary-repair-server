package com.hs.lunpair.domain.user.controller;

import com.hs.lunpair.common.model.StringResponse;
import com.hs.lunpair.core.aop.logging.RequestLogging;
import com.hs.lunpair.domain.user.dto.request.UserAddrCreateRequest;
import com.hs.lunpair.domain.user.dto.request.UserBusinessInfoCreateRequest;
import com.hs.lunpair.domain.user.dto.request.UserCreateRequest;
import com.hs.lunpair.domain.user.dto.request.UserPwUpdateRequest;
import com.hs.lunpair.domain.user.dto.request.UserUpdateRequest;
import com.hs.lunpair.domain.user.dto.response.CustomerUserGetResponse;
import com.hs.lunpair.domain.user.dto.response.SellerUserGetResponse;
import com.hs.lunpair.domain.user.dto.response.UserCreateResponse;
import com.hs.lunpair.domain.user.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserCreateService userCreateService;
    private final UserAddrCreateService userAddrCreateService;
    private final UserBusinessInfoCreateService userBusinessInfoCreateService;
    private final UserGetService userGetService;
    private final UserCommonService userCommonService;
    private final UserUpdateService  userUpdateService;
    private final UserDeleteService userDeleteService;

    @RequestLogging
    @ApiOperation("회원가입")
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        return new UserCreateResponse(userCreateService.createUser(userCreateRequest),HttpStatus.CREATED);
    }

    @RequestLogging
    @ApiOperation("일반회원 주소등록")
    @PostMapping("/address")
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createUserAddr(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid  UserAddrCreateRequest userAddrCreateRequest){
        return new StringResponse(userAddrCreateService
                .createUserAddr(userAddrCreateRequest,accessToken), HttpStatus.CREATED);
    }

    @RequestLogging
    @ApiOperation("판매자 정보등록")
    @PostMapping("/businessInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createUserBusinessInfo(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid UserBusinessInfoCreateRequest userBusinessInfoCreateRequest){
        return new StringResponse(userBusinessInfoCreateService
                .createUserBusinessInfo(userBusinessInfoCreateRequest,accessToken), HttpStatus.CREATED);
    }

    @RequestLogging
    @ApiOperation("일반 유저정보 가져오기")
    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public CustomerUserGetResponse getCustomerUser(@RequestHeader("accessToken") String accessToken){
        return userGetService.getCustomerUser(accessToken,HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("판매자 유저정보 가져오기")
    @GetMapping("/seller")
    @ResponseStatus(HttpStatus.OK)
    public SellerUserGetResponse getSellerUser(@RequestHeader("accessToken") String accessToken){
        return userGetService.getSellerUser(accessToken,HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("아이디 찾기")
    @GetMapping("/identity")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse getUserIdentity(
            @RequestParam("userName") String name,
            @RequestParam("phoneNumber") String phoneNumber){
        return new StringResponse(userCommonService.findUserEmailByPhoneNumber1(name,phoneNumber),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("비밀번호 변경")
    @PatchMapping("/pw")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse patchUserPw(
            @RequestBody @Valid UserPwUpdateRequest userPwUpdateRequest){
        return new StringResponse(userCommonService.changeUserPassword(userPwUpdateRequest),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("사용자 기본정보 변경")
    @PutMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse putUserIdentity(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid UserUpdateRequest userUpdateRequest){
        return new StringResponse(userUpdateService.updateUserCommonInfo(userUpdateRequest,accessToken),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("사용자 대표 주소지 변경")
    @PatchMapping("/represent/address")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse patchUserRepresentAddr(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid Long userAddressId){
        return new StringResponse(userUpdateService.changeRepresentAddr(userAddressId,accessToken),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("사용자 주소 삭제")
    @DeleteMapping("/address")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse deleteUserAddr(
            @RequestHeader("accessToken") String accessToken,
            @RequestBody @Valid Long userAddressId){
        return new StringResponse(userDeleteService.deleteUserAddr(userAddressId,accessToken),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("일반 사용자 회원탈퇴")
    @DeleteMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse deleteCustomerUser(
            @RequestHeader("accessToken") String accessToken){
        return new StringResponse(userDeleteService.deleteCustomerUser(accessToken),HttpStatus.OK);
    }

    @RequestLogging
    @ApiOperation("판매자 회원탈퇴")
    @DeleteMapping("/seller")
    @ResponseStatus(HttpStatus.OK)
    public StringResponse deleteSellerUser(
            @RequestHeader("accessToken") String accessToken){
        return new StringResponse(userDeleteService.deleteSellerUser(accessToken),HttpStatus.OK);
    }

}
