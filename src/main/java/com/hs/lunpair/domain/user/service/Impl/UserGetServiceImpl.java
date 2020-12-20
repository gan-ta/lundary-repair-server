package com.hs.lunpair.domain.user.service.Impl;

import com.hs.lunpair.core.security.jwt.JwtCore;
import com.hs.lunpair.domain.user.dto.response.CustomerUserGetResponse;
import com.hs.lunpair.domain.user.dto.response.SellerUserGetResponse;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.entity.UserAddress;
import com.hs.lunpair.domain.user.entity.UserBusinessInfo;
import com.hs.lunpair.domain.user.exception.BusinessInfoNotFoundException;
import com.hs.lunpair.domain.user.repository.UserAddressRepository;
import com.hs.lunpair.domain.user.repository.UserBusinessInfoRepository;
import com.hs.lunpair.domain.user.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {

    private final UserAddressRepository userAddressRepository;
    private final UserBusinessInfoRepository userBusinessInfoRepository;
    private final JwtCore jwtCore;

    /**
     * 고객 사용자의 정보 조회
     * @param accessToken 사용자 토큰
     * @param status 통신상태
     * @return 고객 사용자의 정보
     */
    @Override
    public CustomerUserGetResponse getCustomerUser(String accessToken, HttpStatus status) {
        User user = jwtCore.findUserByToken(accessToken);
        List<UserAddress> userAddressesList = userAddressRepository.findByUser(user);

        return CustomerUserGetResponse.builder()
                .user(user)
                .userAddressList(userAddressesList)
                .httpStatus(status)
                .build();
    }

    /**
     * 판매자 사용자의 정보 조회
     * @param accessToken 사용자 토큰
     * @param status 통신상태
     * @return 판매자 사용자의 정보
     */
    @Override
    public SellerUserGetResponse getSellerUser(String accessToken, HttpStatus status) {
        User user = jwtCore.findUserByToken(accessToken);
        UserBusinessInfo userBusinessInfo = userBusinessInfoRepository.findByUser(user)
                .orElseThrow(BusinessInfoNotFoundException::new);

        return SellerUserGetResponse.builder()
                .user(user)
                .userBusinessInfo(userBusinessInfo)
                .httpStatus(status)
                .build();

    }
}
