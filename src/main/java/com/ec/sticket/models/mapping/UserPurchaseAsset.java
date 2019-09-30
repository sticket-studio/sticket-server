package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserPurchaseAssetKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserPurchaseAsset implements Serializable {

    @EmbeddedId
    UserPurchaseAssetKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("asset_id")
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private LocalDateTime purchaseTime;

    public UserPurchaseAsset(UserPurchaseAssetKey id) {
        this.id = id;
        this.purchaseTime = LocalDateTime.now();
    }
}
