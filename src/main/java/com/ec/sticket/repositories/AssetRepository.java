package com.ec.sticket.repositories;

import com.ec.sticket.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    List<Asset> findAllByAuthorId(int authorId);

    @Query(value = "SELECT a FROM asset a INNER JOIN user_asset_purchase uap ON a.id = uap.asset_id WHERE uap.user_id = ?1")
    List<Asset> getAllByBuyerId(@Param("buyerId") int buyerId);

    @Query(value = "SELECT a FROM asset a INNER JOIN sticker_asset sa ON a.id = sa.asset_id WHERE sa.sticker_id = ?1")
    List<Asset> getAllByStickerId(@Param("stickerId") int stickerId);

    List<Asset> findAllByLandmarkId(int LandmarkId);

    @Query(value = "SELECT a FROM asset a INNER JOIN asset_theme at ON a.id = at.asset_id WHERE at.theme_id = ?1")
    List<Asset> getAllByThemeId(@Param("themeId") int themeId);
}