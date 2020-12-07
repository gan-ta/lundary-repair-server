package com.hs.lunpair.domain.user.repository;

import com.hs.lunpair.domain.user.entity.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessInfoRepository extends JpaRepository<BusinessInfo,Long> {
}
