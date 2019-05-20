package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserSticonPurchaseKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
@IdClass(value= UserSticonPurchaseKey.class)
public class UserSticonPurchase {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id"
            , referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "sticon_id"
            , referencedColumnName = "id")
    private Sticon sticon;

    private int price;
    private LocalDateTime purchaseTime;

    public UserSticonPurchase(User user, Sticon sticon, int price, LocalDateTime purchaseTime) {
        this.user = user;
        this.sticon = sticon;
        this.price = price;
        this.purchaseTime = purchaseTime;
    }
}
