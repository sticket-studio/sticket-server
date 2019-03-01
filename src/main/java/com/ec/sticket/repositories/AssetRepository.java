package com.ec.sticket.repositories;

import com.ec.sticket.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    List<Asset> findAllByAuthorIdx(int authorIdx);

    @Query("SELECT a FROM asset a INNER JOIN user_asset_purchase uap ON a.idx = uap.asset_idx WHERE uap.user_idx = :buyerIdx")
    List<Asset> findAllByBuyerIdx(@Param("buyerIdx") int buyerIdx);

    @Query("SELECT a FROM asset a INNER JOIN sticker_asset sa ON a.idx = sa.asset_idx WHERE sa.sticker_idx = :stickerIdx")
    List<Asset> findAllByStickerIdx(@Param("stickerIdx") int stickerIdx);

    List<Asset> findAllByLandmarkIdx(int LandmarkIdx);

    @Query("SELECT a FROM asset a INNER JOIN asset_theme at ON a.idx = at.asset_idx WHERE at.theme_idx = :themeIdx")
    List<Asset> findAllByThemeIdx(@Param("themeIdx") int themeIdx);
}