package com.hs.yourfit.domain.dress.service;

import com.hs.yourfit.domain.dress.dto.response.PantsGetResponse;
import com.hs.yourfit.domain.dress.dto.response.TopGetResponse;
import org.springframework.http.HttpStatus;

public interface DressGetService {
    PantsGetResponse getPants(String accessToken, HttpStatus httpStatus);
    TopGetResponse getTop(String accessToken, HttpStatus httpStatus);
}
