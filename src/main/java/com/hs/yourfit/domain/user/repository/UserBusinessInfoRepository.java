package com.hs.yourfit.domain.user.repository;

import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserBusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBusinessInfoRepository extends JpaRepository<UserBusinessInfo,Long> {
    UserBusinessInfo save(UserBusinessInfo userBusinessInfo);
    Optional<UserBusinessInfo> findByUser(User user);
}
