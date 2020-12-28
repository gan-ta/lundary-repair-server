package com.hs.yourfit.domain.user.service;

import com.hs.yourfit.domain.user.dto.request.UserAddrCreateRequest;

public interface UserAddrCreateService {
    String createUserAddr(UserAddrCreateRequest userAddrCreateRequest, String accessToken);
}
