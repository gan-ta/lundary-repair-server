package com.hs.yourfit.domain.dress.service.Impl;

import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.dress.dto.request.PantsUpdateRequest;
import com.hs.yourfit.domain.dress.dto.request.TopUpdateRequest;
import com.hs.yourfit.domain.dress.entity.Pants;
import com.hs.yourfit.domain.dress.entity.Top;
import com.hs.yourfit.domain.dress.entity.enums.LengthCategory;
import com.hs.yourfit.domain.dress.exception.PantsNotFoundException;
import com.hs.yourfit.domain.dress.exception.TopNotFoundException;
import com.hs.yourfit.domain.dress.repository.PantsRepository;
import com.hs.yourfit.domain.dress.repository.TopRepository;
import com.hs.yourfit.domain.dress.service.DressUpdateService;
import com.hs.yourfit.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DressUpdateServiceImpl implements DressUpdateService {

    private final TopRepository topRepository;
    private final PantsRepository pantsRepository;
    private final JwtCore jwtCore;

    /**
     * 개인의 상의 수선정보 수정
     * @param topUpdateRequest 수정 정보
     * @param accessToken 사용자 토큰
     * @return 성공 혹은 실패 메세지
     */
    @Override
    public String updateTop(TopUpdateRequest topUpdateRequest, String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        Top top = topRepository.fetchById(topUpdateRequest.getTopId()).orElseThrow(TopNotFoundException::new);
        if (user.getId() != top.getUser().getId()) return "권한이 없는 회원입니다.";

        top.updateTop(
                LengthCategory.findByString(topUpdateRequest.getLengthCategory()),
                topUpdateRequest.getLength(),
                topUpdateRequest.getShoulder(),
                topUpdateRequest.getChest(),
                topUpdateRequest.getRetail()
        );

        return "success";
    }

    /**
     * 개인의 하의 수선정보 수정
     * @param pantsUpdateRequest 수선 정보
     * @param accessToken 사용자 토큰
     * @return 성공 혹은 실패 메세지
     */
    @Override
    public String updatePants(PantsUpdateRequest pantsUpdateRequest, String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        Pants pants = pantsRepository.fetchById(pantsUpdateRequest.getPantsId()).orElseThrow(PantsNotFoundException::new);
        if(user.getId() != pants.getUser().getId()) return "권한이 없는 회원입니다.";

        pants.updatePants(
                LengthCategory.findByString(pantsUpdateRequest.getLengthCategory()),
                pantsUpdateRequest.getLength(),
                pantsUpdateRequest.getWaist(),
                pantsUpdateRequest.getThigh(),
                pantsUpdateRequest.getCrotch(),
                pantsUpdateRequest.getTailEdge()
        );

        return "success";
    }
}
