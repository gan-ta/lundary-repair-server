package com.hs.yourfit.domain.user.service;

import com.hs.yourfit.domain.user.dto.response.CustomerUserGetResponse;
import com.hs.yourfit.domain.user.dto.response.SellerUserGetResponse;
import org.springframework.http.HttpStatus;

public interface UserGetService {
    CustomerUserGetResponse getCustomerUser(String accessToken, HttpStatus status);
    SellerUserGetResponse getSellerUser(String accessToken, HttpStatus status);
}
