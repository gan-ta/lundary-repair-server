package com.hs.lunpair.domain.user.service.Impl;

import com.hs.lunpair.core.security.jwt.JwtCore;
import com.hs.lunpair.domain.user.entity.BusinessInfo;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.repository.BusinessInfoRepository;
import com.hs.lunpair.domain.user.repository.UserRepository;
import com.hs.lunpair.domain.user.service.UserCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final UserRepository userRepository;
    private final BusinessInfoRepository businessInfoRepository;
    private final JwtCore jwtCore;

    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return jwtCore.createAccessToken(user.getEmail(),user.getUserRole());
    }

    @Override
    public void createBusinessInfo(User user,BusinessInfo businessInfo) {
    }
}
