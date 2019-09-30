package com.ec.sticket.repositories.mapping.purchase;

import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserPurchaseAsset;
import com.ec.sticket.models.mapping.compositekey.UserPurchaseAssetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserPurchaseAssetRepository extends JpaRepository<UserPurchaseAsset, UserPurchaseAssetKey> {
    List<UserPurchaseAsset> findAllByUser(User user);

    UserPurchaseAsset findByPurchaseTimeAfterAndPurchaseTimeBefore(LocalDateTime startTime, LocalDateTime endTime);
}
