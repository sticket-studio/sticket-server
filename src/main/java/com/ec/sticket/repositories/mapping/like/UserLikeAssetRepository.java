package com.ec.sticket.repositories.mapping.like;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserLikeAsset;
import com.ec.sticket.models.mapping.compositekey.UserLikeAssetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLikeAssetRepository extends JpaRepository<UserLikeAsset, UserLikeAssetKey> {
    public List<UserLikeAsset> findAllByUser(User user);

    public List<UserLikeAsset> findAllByAsset(Asset asset);
}
