package com.hs.yourfit.domain.user.service.Impl;

import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.user.dto.request.UserAddrCreateRequest;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.repository.UserAddressRepository;
import com.hs.yourfit.domain.user.service.UserAddrCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAddrCreateServiceImpl implements UserAddrCreateService {

    private final UserAddressRepository userAddressRepository;
    private final JwtCore jwtCore;

    /**
     * 유저의 주소지 생성
     * @param userAddrCreateRequest 주소 정보
     * @param accessToken 사용자 토큰
     * @return 성공 메세지
     */
    @Override
    public String createUserAddr(UserAddrCreateRequest userAddrCreateRequest,String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        if(!userAddressRepository.existsByUser(user)){
            createRepresentAddr(userAddrCreateRequest,user);
        }else{
            createAnotherAddr(userAddrCreateRequest,user);
        }
        return "success";
    }

    /**
     * 대표 주소지 등록
     * @param userAddrCreateRequest 주소 등로 요청 정보
     * @param user 사용자 객체
     */
    private void createRepresentAddr(UserAddrCreateRequest userAddrCreateRequest, User user){
        userAddressRepository.save(userAddrCreateRequest.representOf(user));
    }

    /**
     * 일반 주소 등록
     * @param userAddrCreateRequest 주소 등로 요청 정보
     * @param user 사용자 객체
     */
    private void createAnotherAddr(UserAddrCreateRequest userAddrCreateRequest, User user){
        userAddressRepository.save(userAddrCreateRequest.anotherOf(user));
    }
}
