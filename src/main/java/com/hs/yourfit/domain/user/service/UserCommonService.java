package com.hs.yourfit.domain.user.service;

import com.hs.yourfit.domain.user.dto.request.UserPwUpdateRequest;

public interface UserCommonService {
    String findUserEmailByPhoneNumber1(String name, String phoneNumber);
    String changeUserPassword(UserPwUpdateRequest userPwUpdateRequest);
}
