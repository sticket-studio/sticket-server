package com.ec.sticket.models.mapping.compositekey;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class UserPurchaseAssetKey implements Serializable {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "asset_id")
    private int assetId;
}