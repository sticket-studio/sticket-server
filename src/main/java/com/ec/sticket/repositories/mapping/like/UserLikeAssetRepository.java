package com.ec.sticket.repositories.mapping.like;

import com.ec.sticket.models.mapping.UserLikeAsset;
import com.ec.sticket.models.mapping.compositekey.UserLikeAssetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeAssetRepository extends JpaRepository<UserLikeAsset, UserLikeAssetKey> {
}
