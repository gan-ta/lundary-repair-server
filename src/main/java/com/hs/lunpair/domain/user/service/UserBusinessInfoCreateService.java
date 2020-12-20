package com.hs.lunpair.domain.user.service;

import com.hs.lunpair.domain.user.dto.request.UserBusinessInfoCreateRequest;

public interface UserBusinessInfoCreateService {
    String createUserBusinessInfo(UserBusinessInfoCreateRequest userBusinessInfoCreateRequest, String accessToken);
}
