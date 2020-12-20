package com.hs.lunpair.domain.user.service.Impl;

import com.hs.lunpair.core.security.jwt.JwtCore;
import com.hs.lunpair.domain.user.dto.request.UserBusinessInfoCreateRequest;
import com.hs.lunpair.domain.user.repository.UserBusinessInfoRepository;
import com.hs.lunpair.domain.user.service.UserBusinessInfoCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusinessInfoCreateServiceImpl implements UserBusinessInfoCreateService {

    private final UserBusinessInfoRepository userBusinessInfoRepository;
    private final JwtCore jwtCore;

    /**
     * 유저의 사업자 등록 정보 등록
     * @param userBusinessInfoCreateRequest 사용자 사업자 등록 정보
     * @param accessToken 사용자 토큰
     * @return 성공 메세지
     */
    @Override
    public String createUserBusinessInfo(UserBusinessInfoCreateRequest userBusinessInfoCreateRequest, String accessToken){
        userBusinessInfoRepository.save(userBusinessInfoCreateRequest.of(jwtCore.findUserByToken(accessToken)));
        return "success";
    }
}
