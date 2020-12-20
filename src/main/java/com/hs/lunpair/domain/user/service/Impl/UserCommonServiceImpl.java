package com.hs.lunpair.domain.user.service.Impl;

import com.hs.lunpair.domain.user.dto.request.UserPwUpdateRequest;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.entity.UserPhone;
import com.hs.lunpair.domain.user.exception.UserNotFoundException;
import com.hs.lunpair.domain.user.repository.UserRepository;
import com.hs.lunpair.domain.user.service.UserCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommonServiceImpl implements UserCommonService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 유저의 대표 전화번호를 이용하여 등록된 이메일 찾기
     * @param name 사용자 이름
     * @param phoneNumber 사용자 번호
     * @return 등록된 사용자의 이메일
     */
    @Override
    public String findUserEmailByPhoneNumber1(String name, String phoneNumber) {
        Optional<User> user = userRepository.findByNameAndPhoneNumber1(
                name,new UserPhone(phoneNumber));

        //TODO 인증 문자 발송 구현

        return user.get().getEmail();
    }

    /**
     * 사용자의 비밀번호 변경
     * @param userPwUpdateRequest 비밀번호 변경 요청 요구 정보
     * @return 성공 메세지
     */
    @Override
    public String changeUserPassword(UserPwUpdateRequest userPwUpdateRequest) {
        User user = userRepository.findByEmail(userPwUpdateRequest.getEmail()).orElseThrow(UserNotFoundException::new);

        //TODO 인증 문자 발송
        user.updatePw(passwordEncoder.encode(userPwUpdateRequest.getPhoneNumber1()));

        return "success";
    }
}
