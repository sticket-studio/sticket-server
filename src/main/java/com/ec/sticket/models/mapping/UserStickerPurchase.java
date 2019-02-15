package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserStickerPurchase {

    @ManyToOne
    @JoinColumn(name = "user_idx"
            , referencedColumnName = "idx")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sticker_idx"
            , referencedColumnName = "idx")
    private Sticker sticker;

    private int price;
    private LocalDateTime purchaseTime;
}
