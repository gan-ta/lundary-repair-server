package com.hs.lunpair.domain.user.service;

import com.hs.lunpair.domain.user.dto.request.UserCreateRequest;

public interface UserCreateService {
    String createUser(UserCreateRequest userCreateRequest);
}
