package com.hs.yourfit.domain.dress.service;

import com.hs.yourfit.domain.dress.dto.request.PantsCreateRequest;
import com.hs.yourfit.domain.dress.dto.request.TopCreateRequest;

public interface DressCreateService {
    String createPants(PantsCreateRequest pantsCreateRequest, String accessToken);
    String createTop(TopCreateRequest topCreateRequest, String accessToken);
}
