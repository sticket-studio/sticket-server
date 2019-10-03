package com.ec.sticket.repositories;

import com.ec.sticket.models.Asset;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    @Query(value = "SELECT a FROM Asset a WHERE (:authorId = 0 or a.author.id = :authorId) AND " +
//            "(:buyerId = 0 or a.author.id = :buyerId) AND " +
            "(:landmark LIKE '' or a.landmark LIKE :landmark) AND " +
            "(:themeId = 0 or a.theme.id = :themeId) AND :buyerId=:buyerId")
    List<Asset> findAllByQuery(int authorId, int buyerId, String landmark, int themeId);

    List<Asset> findAllByPriceBetween(int priceLow, int priceHigh);


    @Modifying
    @Query(value = "INSERT INTO user_like_asset VALUES(:userId, :assetId, now())", nativeQuery = true)
    void like(@Param("userId") int userId, @Param("assetId") int assetId);

    @Query(value = "SELECT a FROM Asset a INNER JOIN a.userLikeAssets l " +
            " GROUP BY a ORDER BY COUNT(a) DESC")
    List<Asset> findTodayAssets(Pageable pageable);

    @Query(value = "SELECT a FROM Asset a INNER JOIN a.userPurchaseAssets p GROUP BY a ORDER BY COUNT(a) DESC")
    List<Asset> findPopularAssets(Pageable pageable);

    @Query(value = "SELECT a FROM Asset a ORDER BY a.createdTime DESC")
    List<Asset> findNewAssets(Pageable pageable);
}