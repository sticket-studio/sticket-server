package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserAssetPurchaseKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(value= UserAssetPurchaseKey.class)
public class UserAssetPurchase {


    @Id
    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "asset_idx")
    private Asset asset;

    private int price;
    private LocalDateTime purchaseTime;
}
