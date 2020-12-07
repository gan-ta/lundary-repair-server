package com.hs.lunpair.domain.user.service;

import com.hs.lunpair.domain.user.entity.BusinessInfo;
import com.hs.lunpair.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserCreateService {
    String createUser(User user);
    void createBusinessInfo(User user,BusinessInfo businessInfo);
}
