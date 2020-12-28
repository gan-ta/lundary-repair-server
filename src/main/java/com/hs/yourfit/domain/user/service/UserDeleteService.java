package com.hs.yourfit.domain.user.service;

public interface UserDeleteService {
    String deleteUserAddr(Long UserAddressId, String accessToken);
    String deleteCustomerUser(String accessToken);
    String deleteSellerUser(String accessToken);
}
