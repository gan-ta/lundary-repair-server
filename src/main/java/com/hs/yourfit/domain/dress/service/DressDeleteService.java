package com.hs.yourfit.domain.dress.service;

import org.springframework.http.HttpStatus;

public interface DressDeleteService {
    String deleteTop(Long topId, String accessToken);
    String deletePants(Long pantsId, String accessToken);
}
