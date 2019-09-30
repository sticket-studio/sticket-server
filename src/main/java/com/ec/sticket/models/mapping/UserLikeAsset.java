package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserLikeAssetKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserLikeAsset {

    @EmbeddedId
    UserLikeAssetKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("asset_id")
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private LocalDateTime likeTime;

    public UserLikeAsset(UserLikeAssetKey id) {
        this.id = id;
        this.likeTime = LocalDateTime.now();
    }
}
