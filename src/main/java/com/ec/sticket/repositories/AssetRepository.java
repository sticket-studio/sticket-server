package com.ec.sticket.repositories;

import com.ec.sticket.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    Optional<List<Asset>> findAllByAuthorId(int authorId);

    List<Asset> findAllByPriceBetween(int priceLow, int priceHigh);

    @Query(value = "SELECT a FROM Asset a INNER JOIN a.userAssetPurchases uap WHERE uap.user.id = :buyerId")
    List<Asset> findAllByBuyerId(@Param("buyerId") int buyerId);

    @Query(value = "SELECT a FROM Asset a INNER JOIN a.sticonAssets sa WHERE sa.sticon.id = :sticonId")
    List<Asset> findAllBySticonId(@Param("sticonId") int sticonId);

    List<Asset> findAllByLandmark(Asset.Landmark landmark);

    @Query(value = "SELECT a FROM Asset a INNER JOIN a.themes t WHERE t.id = :themeId")
    List<Asset> findAllByThemeId(@Param("themeId") int themeId);

    @Modifying
    @Query(value = "INSERT INTO user_like_asset VALUES(:userId, :assetId)", nativeQuery = true)
    void like(@Param("userId") int userId, @Param("assetId") int assetId);
}