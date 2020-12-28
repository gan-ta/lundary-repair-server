package com.hs.yourfit.domain.user.repository;

import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNameAndPhoneNumber1(String name, UserPhone userPhone);
    User save(User suer);
}
