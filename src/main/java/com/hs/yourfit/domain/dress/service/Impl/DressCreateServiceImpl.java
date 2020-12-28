package com.hs.yourfit.domain.dress.service.Impl;

import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.dress.dto.request.PantsCreateRequest;
import com.hs.yourfit.domain.dress.dto.request.TopCreateRequest;
import com.hs.yourfit.domain.dress.repository.PantsRepository;
import com.hs.yourfit.domain.dress.repository.TopRepository;
import com.hs.yourfit.domain.dress.service.DressCreateService;
import com.hs.yourfit.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DressCreateServiceImpl implements DressCreateService {

    private final JwtCore jwtCore;
    private final PantsRepository pantsRepository;
    private final TopRepository topRepository;

    /**
     * 하의 수선정보 생성
     * @param pantsCreateRequest 수선정보
     * @param accessToken 사용자 토큰
     * @return 성공 메세지
     */
    @Override
    public String createPants(PantsCreateRequest pantsCreateRequest, String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        pantsRepository.save(pantsCreateRequest.of(user));
        return "success";
    }

    /**
     * 상의 수선정보 생성
     * @param topCreateRequest 수선정보
     * @param accessToken 사용자 토큰
     * @return 성공 메세지
     */
    @Override
    public String createTop(TopCreateRequest topCreateRequest, String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        topRepository.save(topCreateRequest.of(user));
        return "success";
    }
}
