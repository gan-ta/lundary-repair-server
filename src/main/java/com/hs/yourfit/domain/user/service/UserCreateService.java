package com.hs.yourfit.domain.user.service;

import com.hs.yourfit.domain.user.dto.request.UserCreateRequest;

public interface UserCreateService {
    String createUser(UserCreateRequest userCreateRequest);
}
