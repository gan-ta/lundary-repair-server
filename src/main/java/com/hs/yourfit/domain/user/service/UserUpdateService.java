package com.hs.yourfit.domain.user.service;

import com.hs.yourfit.domain.user.dto.request.UserUpdateRequest;

public interface UserUpdateService {
    String updateUserCommonInfo(UserUpdateRequest userUpdateRequest, String accessToken);
    String changeRepresentAddr(Long UserAddressId, String accessToken);
}
