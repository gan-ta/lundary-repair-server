package com.hs.yourfit.domain.dress.service;

import com.hs.yourfit.domain.dress.dto.request.PantsUpdateRequest;
import com.hs.yourfit.domain.dress.dto.request.TopUpdateRequest;

public interface DressUpdateService {
    String updateTop(TopUpdateRequest topUpdateRequest, String accessToken);
    String updatePants(PantsUpdateRequest pantsUpdateRequest, String accessToken);
}
