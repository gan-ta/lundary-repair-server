package com.hs.lunpair.domain.user.service.Impl;

import com.hs.lunpair.core.security.jwt.JwtCore;
import com.hs.lunpair.domain.user.dto.request.UserCreateRequest;
import com.hs.lunpair.domain.user.entity.User;
import com.hs.lunpair.domain.user.repository.UserRepository;
import com.hs.lunpair.domain.user.service.UserCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final UserRepository userRepository;
    private final JwtCore jwtCore;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String createUser(UserCreateRequest userCreateRequest) {
        User user = userRepository.save(userCreateRequest.of(passwordEncoder.encode(userCreateRequest.getPassword())));
        return jwtCore.createAccessToken(user.getEmail(),user.getUserRole());
    }

}
