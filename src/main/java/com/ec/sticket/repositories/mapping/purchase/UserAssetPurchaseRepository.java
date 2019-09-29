package com.ec.sticket.repositories.mapping.purchase;

import com.ec.sticket.models.mapping.UserAssetPurchase;
import com.ec.sticket.models.mapping.compositekey.UserAssetPurchaseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserAssetPurchaseRepository extends JpaRepository<UserAssetPurchase, UserAssetPurchaseKey> {
    UserAssetPurchase findByPurchaseTimeAfterAndPurchaseTimeBefore(LocalDateTime startTime, LocalDateTime endTime);
}
