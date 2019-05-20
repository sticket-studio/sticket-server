package com.ec.sticket.repositories;

import com.ec.sticket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Query(value = "SELECT u FROM User u INNER JOIN u.sellingAssets a WHERE a.id = :assetId")
    List<User> findAllBySellingAssetId(@Param("assetId") int assetId);

    @Query(value = "SELECT u FROM User u INNER JOIN u.sellingSticons s WHERE s.id = :sticonId")
    List<User> findAllBySellingSticonId(@Param("sticonId") int sticonId);

    @Query(value = "SELECT u FROM User u INNER JOIN u.sellingMotionticons m WHERE m.id = :motionticonId")
    List<User> findAllBySellingMotionticonId(@Param("motionticonId") int motionticonId);

    @Query(value = "SELECT u FROM User u INNER JOIN u.userAssetPurchases a WHERE a.id = :assetId")
    List<User> findAllByPurchasedAssetId(@Param("assetId") int assetId);

    @Query(value = "SELECT u FROM User u INNER JOIN u.userSticonPurchases s WHERE s.id = :sticonId")
    List<User> findAllByPurchasedSticonId(@Param("sticonId") int sticonId);

    @Query(value = "SELECT u FROM User u INNER JOIN u.userMotionticonPurchases m WHERE m.id = :motionticonId")
    List<User> findAllByPurchasedMotionticonId(@Param("motionticonId") int motionticonId);

    @Query(value = "SELECT u FROM User u INNER JOIN u.userCashItemPurchases c WHERE c.id = :cashItemId")
    List<User> findAllByPurchasedCashItemId(@Param("cashItemId") int cashItemId);
}