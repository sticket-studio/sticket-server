package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserAssetPurchaseKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
@IdClass(value= UserAssetPurchaseKey.class)
public class UserAssetPurchase {


    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private int price;
    private LocalDateTime purchaseTime;

    public UserAssetPurchase(User user, Asset asset, int price) {
        this.user = user;
        this.asset = asset;
        this.price = price;
        this.purchaseTime = LocalDateTime.now();
    }
}
