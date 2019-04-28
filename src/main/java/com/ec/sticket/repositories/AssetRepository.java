package com.ec.sticket.repositories;

import com.ec.sticket.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query(value = "SELECT a FROM Asset a INNER JOIN a.stickerAssets sa WHERE sa.sticker.id = :stickerId")
    List<Asset> findAllByStickerId(@Param("stickerId") int stickerId);

    List<Asset> findAllByLandmark(Asset.Landmark landmark);

    @Query(value = "SELECT a FROM Asset a INNER JOIN a.themes t WHERE t.id = :themeId")
    List<Asset> findAllByThemeId(@Param("themeId") int themeId);
}