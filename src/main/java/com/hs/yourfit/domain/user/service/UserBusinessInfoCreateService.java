package com.hs.yourfit.domain.user.service;

import com.hs.yourfit.domain.user.dto.request.UserBusinessInfoCreateRequest;

public interface UserBusinessInfoCreateService {
    String createUserBusinessInfo(UserBusinessInfoCreateRequest userBusinessInfoCreateRequest, String accessToken);
}
