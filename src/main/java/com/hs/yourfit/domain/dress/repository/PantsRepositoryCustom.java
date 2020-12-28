package com.hs.yourfit.domain.dress.repository;

import com.hs.yourfit.domain.dress.entity.Pants;

import java.util.Optional;

public interface PantsRepositoryCustom {
    Optional<Pants> fetchById(Long pantsId);
}
