package com.ec.sticket.repositories;

import com.ec.sticket.models.Motionticon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotionticonRepository extends JpaRepository<Motionticon, Integer> {
    List<Motionticon> findAllByAuthorId(int authorId);

    @Query(value = "SELECT m FROM Motionticon m INNER JOIN m.userMotionticonPurchases usp WHERE usp.user.id = :buyerId")
    List<Motionticon> findAllByBuyerId(@Param("buyerId") int buyerId);


    @Query(value = "SELECT m FROM Motionticon m INNER JOIN m.motionticonStickers ms WHERE ms.motionticon.id = :motionticonId")
    List<Motionticon> findAllByMotionticonId(@Param("motionticonId") int motionticonId);

    /*
    @Query(value ="SELECT m FROM Motionticon  m INNER JOIN m.stickers s WHERE s.sticker.id = :stickerId")
    List<Motionticon> findAllByStickerId(@Param("stickerId") int stickerId);

    @Query(value = "SELECT m FROM Motionticon m INNER JOIN m.stickerAssets sa WHERE sa.asset.id = :assetId")
    List<Motionticon> findAllByAssetId(@Param("assetId") int assetId);
    */


    @Query(value = "SELECT m FROM Motionticon m INNER JOIN m.themes t WHERE t.id = :themeId")
    List<Motionticon> findAllByThemeId(@Param("themeId") int themeId);

}