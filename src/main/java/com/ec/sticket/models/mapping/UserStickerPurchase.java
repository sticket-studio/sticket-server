package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserStickerPurchaseKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
@IdClass(value= UserStickerPurchaseKey.class)
public class UserStickerPurchase {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id"
            , referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "sticker_id"
            , referencedColumnName = "id")
    private Sticker sticker;

    private int price;
    private LocalDateTime purchaseTime;

    public UserStickerPurchase(User user, Sticker sticker, int price, LocalDateTime purchaseTime) {
        this.user = user;
        this.sticker = sticker;
        this.price = price;
        this.purchaseTime = purchaseTime;
    }
}
