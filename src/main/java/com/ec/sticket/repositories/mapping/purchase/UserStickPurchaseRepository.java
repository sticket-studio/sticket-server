package com.ec.sticket.repositories.mapping.purchase;

import com.ec.sticket.models.mapping.UserPurchaseAsset;
import com.ec.sticket.models.mapping.UserStickPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserStickPurchaseRepository extends JpaRepository<UserStickPurchase, Integer> {
    UserPurchaseAsset findByPurchaseTimeAfterAndPurchaseTimeBefore(LocalDateTime startTime, LocalDateTime endTime);
}
