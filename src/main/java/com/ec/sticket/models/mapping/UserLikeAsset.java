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
@IdClass(value = UserLikeAssetKey.class)
public class UserLikeAsset {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id"
            , referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id"
            , referencedColumnName = "id")
    private Asset asset;

    private LocalDateTime likeTime;

    public UserLikeAsset(User user, Asset asset) {
        this.user = user;
        this.asset = asset;
        this.likeTime = LocalDateTime.now();
    }
}
