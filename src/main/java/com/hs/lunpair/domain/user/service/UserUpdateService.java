package com.hs.lunpair.domain.user.service;

import com.hs.lunpair.domain.user.dto.request.UserUpdateRequest;

public interface UserUpdateService {
    String updateUserCommonInfo(UserUpdateRequest userUpdateRequest,String accessToken);
    String changeRepresentAddr(Long UserAddressId, String accessToken);
}
