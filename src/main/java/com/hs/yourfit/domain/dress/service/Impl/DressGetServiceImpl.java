package com.hs.yourfit.domain.dress.service.Impl;

import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.dress.dto.response.PantsGetResponse;
import com.hs.yourfit.domain.dress.dto.response.TopGetResponse;
import com.hs.yourfit.domain.dress.repository.PantsRepository;
import com.hs.yourfit.domain.dress.repository.TopRepository;
import com.hs.yourfit.domain.dress.service.DressGetService;
import com.hs.yourfit.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DressGetServiceImpl implements DressGetService {

    private final TopRepository topRepository;
    private final PantsRepository pantsRepository;
    private final JwtCore jwtCore;

    /**
     * 개인의 하의 수선 등록정보를 가져옴
     * @param accessToken 사용자 토큰
     * @param httpStatus 통신상태
     * @return 개인의 하의 수선정보 응답 객체
     */
    @Override
    public PantsGetResponse getPants(String accessToken, HttpStatus httpStatus) {
        User user = jwtCore.findUserByToken(accessToken);

        return new PantsGetResponse(pantsRepository.findByUser(user),httpStatus);
    }

    /**
     * 개인의 상의 수선 등록정보를 가져옴
     * @param accessToken 사용자 토큰
     * @param httpStatus 통신상태
     * @return 개인의 상의 수선정보 응답 객체
     */
    @Override
    public TopGetResponse getTop(String accessToken, HttpStatus httpStatus) {
        User user = jwtCore.findUserByToken(accessToken);

        return new TopGetResponse(topRepository.findByUser(user),httpStatus);
    }
}
