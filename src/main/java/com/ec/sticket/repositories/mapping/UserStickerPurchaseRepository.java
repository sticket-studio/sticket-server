package com.ec.sticket.repositories.mapping;

import com.ec.sticket.models.mapping.UserStickerPurchase;
import com.ec.sticket.models.mapping.compositekey.UserStickerPurchaseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserStickerPurchaseRepository extends JpaRepository<UserStickerPurchase, UserStickerPurchaseKey> {
    UserStickerPurchase findByPurchaseTimeAfterAndPurchaseTimeBefore(LocalDateTime startTime, LocalDateTime endTime);
}
