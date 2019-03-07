package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserMotionticonPurchaseKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
@IdClass(value= UserMotionticonPurchaseKey.class)
public class UserMotionticonPurchase {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id"
            , referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "motionticon_id"
            , referencedColumnName = "id")
    private Motionticon motionticon;

    private int price;
    private LocalDateTime purchaseTime;

    public UserMotionticonPurchase(User user, Motionticon motionticon, int price, LocalDateTime purchaseTime) {
        this.user = user;
        this.motionticon = motionticon;
        this.price = price;
        this.purchaseTime = purchaseTime;
    }
}
