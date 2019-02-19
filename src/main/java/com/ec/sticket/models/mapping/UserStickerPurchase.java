package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserStickerPurchaseKey;
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
@IdClass(value= UserStickerPurchaseKey.class)
public class UserStickerPurchase {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_idx"
            , referencedColumnName = "idx")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "sticker_idx"
            , referencedColumnName = "idx")
    private Sticker sticker;

    private int price;
    private LocalDateTime purchaseTime;
}
