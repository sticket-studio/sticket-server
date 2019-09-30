package com.ec.sticket.repositories;

import com.ec.sticket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Query(value = "SELECT u FROM User u INNER JOIN u.sellingAssets a WHERE a.id = :assetId")
    List<User> findAllBySellingAssetId(@Param("assetId") int assetId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_like_sticon(USER_ID, STICON_ID, LIKE_TIME) VALUES(:userId, :sticonId, NOW())", nativeQuery = true)
    void likeSticon(@Param("userId") int userId, @Param("sticonId") int sticonId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO USER_LIKE_MOTIONTICON(USER_ID, MONTIONTICON_ID, LIKE_TIME) VALUES(:userId, :motionticonId, NOW())", nativeQuery = true)
    void likeMotionticon(@Param("userId") int userId, @Param("motionticonId") int motionticonId);

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

    @Query(value = "SELECT u FROM User u INNER JOIN u.userStickPurchases c WHERE c.id = :stickId")
    List<User> findAllByPurchasedStickId(@Param("stickId") int stickId);
}