package com.hs.yourfit.domain.user.service.Impl;

import com.hs.yourfit.core.error.exception.UserDefineException;
import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.user.dto.request.UserUpdateRequest;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.exception.AddressNotFoundException;
import com.hs.yourfit.domain.user.repository.UserAddressRepository;
import com.hs.yourfit.domain.user.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserUpdateServiceImpl implements UserUpdateService{

    private final JwtCore jwtCore;
    private final UserAddressRepository userAddressRepository;

    /**
     * 유저의 회원 기본정보 변경
     * @param userUpdateRequest 변경 정보 내용
     * @param accessToken 사용자 토큰
     * @return 성공 메세지
     */
    @Override
    public String updateUserCommonInfo(UserUpdateRequest userUpdateRequest, String accessToken) {

        User user = jwtCore.findUserByToken(accessToken);

        user.updateUser(userUpdateRequest);

        return "success";
    }

    /**
     * 대표 주소지 변경
     * @param userAddressId 유저 주소 아이디
     * @param accessToken 사용자 토큰
     * @return 성공 메세지
     */
    @Override
    public String changeRepresentAddr(Long userAddressId, String accessToken) {

        User user = jwtCore.findUserByToken(accessToken);

        if(!userAddressRepository.existsByUserAndId(user,userAddressId)){
            throw new UserDefineException("해당 회원과 주소지가 일치하지 않는 요청입니다.");
        }

        userAddressRepository.findByUserAndRepresent(user,true)
                .forEach(userAddress -> userAddress.updateRepresent(false));

        userAddressRepository.findById(userAddressId).orElseThrow(AddressNotFoundException::new).updateRepresent(true);

        return "Success";
    }
}
