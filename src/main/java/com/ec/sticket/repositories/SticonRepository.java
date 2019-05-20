package com.ec.sticket.repositories;

import com.ec.sticket.models.Sticon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SticonRepository extends JpaRepository<Sticon, Integer> {

    List<Sticon> findAllByAuthorId(int authorId);

    @Query(value = "SELECT s FROM Sticon s INNER JOIN s.userSticonPurchases usp WHERE usp.user.id = :buyerId")
    List<Sticon> findAllByBuyerId(@Param("buyerId") int buyerId);

    @Query(value = "SELECT s FROM Sticon s INNER JOIN s.sticonAssets sa WHERE sa.asset.id = :assetId")
    List<Sticon> findAllByAssetId(@Param("assetId") int assetId);

    @Query(value = "SELECT s FROM Sticon s INNER JOIN s.motionticonSticons ms WHERE ms.motionticon.id = :motionticonId")
    List<Sticon> findAllByMotionticonId(@Param("motionticonId") int motionticonId);

    @Query(value = "SELECT s FROM Sticon s INNER JOIN s.themes t WHERE t.id = :themeId")
    List<Sticon> findAllByThemeId(@Param("themeId") int themeId);
}