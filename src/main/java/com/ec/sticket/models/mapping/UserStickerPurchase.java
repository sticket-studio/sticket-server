package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserStickerPurchase {
    private User user;
    private Sticker sticker;
    private int price;
    private LocalDateTime purchaseTime;
}
