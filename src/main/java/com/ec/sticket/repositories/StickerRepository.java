package com.ec.sticket.repositories;

import com.ec.sticket.models.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Integer> {

    List<Sticker> findAllByAuthorId(int authorId);

    @Query(value = "SELECT s FROM Sticker s INNER JOIN s.userStickerPurchases usp WHERE usp.user.id = :buyerId")
    List<Sticker> findAllByBuyerId(@Param("buyerId") int buyerId);

    @Query(value = "SELECT s FROM Sticker s INNER JOIN s.assets a WHERE a.id = :assetId")
    List<Sticker> findAllByAssetId(@Param("assetId") int assetId);

    @Query(value = "SELECT s FROM Sticker s INNER JOIN s.motionticons m WHERE m.id = :motionticonId")
    List<Sticker> findAllByMotionticonId(@Param("motionticonId") int motionticonId);

    @Query(value = "SELECT s FROM Sticker s INNER JOIN s.themes t WHERE t.id = :themeId")
    List<Sticker> findAllByThemeId(@Param("themeId") int themeId);
}