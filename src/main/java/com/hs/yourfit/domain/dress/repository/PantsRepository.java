package com.hs.yourfit.domain.dress.repository;

import com.hs.yourfit.domain.dress.entity.Pants;
import com.hs.yourfit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PantsRepository extends JpaRepository<Pants, Long>, PantsRepositoryCustom{
    List<Pants> findByUser(User user);
}
