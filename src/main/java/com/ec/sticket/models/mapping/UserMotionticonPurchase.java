package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserMotionticonPurchaseKey;
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
@IdClass(value= UserMotionticonPurchaseKey.class)
public class UserMotionticonPurchase {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_idx"
            , referencedColumnName = "idx")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "motionticon_idx"
            , referencedColumnName = "idx")
    private Motionticon motionticon;

    private int price;
    private LocalDateTime purchaseTime;
}
