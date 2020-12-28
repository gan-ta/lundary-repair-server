package com.hs.yourfit.domain.user.repository;

import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {
    UserAddress save(UserAddress userAddress);
    List<UserAddress> findByUser(User user);
    boolean existsByUser(User user);
    boolean existsByUserAndId(User user,Long userAddressId);
    Optional<UserAddress> findById(Long userAddressId);
    List<UserAddress> findByUserAndRepresent(User user,boolean represent);
}
