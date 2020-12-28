package com.hs.yourfit.domain.dress.repository;

import com.hs.yourfit.domain.dress.entity.Top;
import com.hs.yourfit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopRepository extends JpaRepository<Top, Long> , TopRepositoryCustom{
    List<Top> findByUser(User user);
}
