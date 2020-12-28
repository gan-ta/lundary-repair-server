package com.hs.yourfit.domain.dress.service.Impl;

import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.dress.entity.Pants;
import com.hs.yourfit.domain.dress.entity.Top;
import com.hs.yourfit.domain.dress.exception.PantsNotFoundException;
import com.hs.yourfit.domain.dress.exception.TopNotFoundException;
import com.hs.yourfit.domain.dress.repository.PantsRepository;
import com.hs.yourfit.domain.dress.repository.TopRepository;
import com.hs.yourfit.domain.dress.service.DressDeleteService;
import com.hs.yourfit.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DressDeleteServiceImpl implements DressDeleteService {

    private final TopRepository topRepository;
    private final PantsRepository pantsRepository;
    private final JwtCore jwtCore;

    /**
     * 상의 수선정보 삭제
     * @param topId 수선정보 아이디
     * @param accessToken 사용자 토큰
     * @return 성공 혹은 실패 메세지
     * @exception TopNotFoundException 상의 수선정보를 찾을 수 없음
     */
    @Override
    public String deleteTop(Long topId, String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        Top top = topRepository.fetchById(topId).orElseThrow(TopNotFoundException::new);
        if (user.getId() != top.getUser().getId()) return "권한이 없는 회원입니다.";

        top.delete();

        return "success";
    }

    /**
     * 하의 수선정보 삭제
     * @param pantsId 수선정보 아이디
     * @param accessToken 사용자 토큰
     * @return 성공 혹은 실패 메세지
     * @exception PantsNotFoundException 하의 수선정보를 찾을 수 없음
     */
    @Override
    public String deletePants(Long pantsId, String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        Pants pants = pantsRepository.fetchById(pantsId).orElseThrow(PantsNotFoundException::new);
        if(user.getId() != pants.getUser().getId()) return "권한이 없는 회원입니다.";

        pants.delete();

        return "success";
    }
}
