package com.ec.sticket.repositories.mapping;

import com.ec.sticket.models.mapping.UserSticonPurchase;
import com.ec.sticket.models.mapping.compositekey.UserSticonPurchaseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserSticonPurchaseRepository extends JpaRepository<UserSticonPurchase, UserSticonPurchaseKey> {
    UserSticonPurchase findByPurchaseTimeAfterAndPurchaseTimeBefore(LocalDateTime startTime, LocalDateTime endTime);
}
