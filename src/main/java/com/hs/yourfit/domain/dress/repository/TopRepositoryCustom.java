package com.hs.yourfit.domain.dress.repository;

import com.hs.yourfit.domain.dress.entity.Top;

import java.util.Optional;

public interface TopRepositoryCustom {
    Optional<Top> fetchById(Long topId);
}
