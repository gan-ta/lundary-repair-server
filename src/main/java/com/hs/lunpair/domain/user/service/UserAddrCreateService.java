package com.hs.lunpair.domain.user.service;

import com.hs.lunpair.domain.user.dto.request.UserAddrCreateRequest;

public interface UserAddrCreateService {
    String createUserAddr(UserAddrCreateRequest userAddrCreateRequest, String accessToken);
}
