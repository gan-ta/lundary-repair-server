package com.hs.lunpair.domain.user.service;

import com.hs.lunpair.domain.user.dto.request.UserPwUpdateRequest;

public interface UserCommonService {
    String findUserEmailByPhoneNumber1(String name, String phoneNumber);
    String changeUserPassword(UserPwUpdateRequest userPwUpdateRequest);
}
