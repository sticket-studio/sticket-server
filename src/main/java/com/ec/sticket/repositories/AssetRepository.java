package com.ec.sticket.repositories;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    List<Asset> findAllByAuthor(User user);

    List<Asset> findAllByStickersIn(Sticker sticker);

    List<Asset> findAllByLandmarkIdx(int LandmarkIdx);
}
