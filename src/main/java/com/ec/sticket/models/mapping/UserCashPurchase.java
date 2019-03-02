package com.ec.sticket.models.mapping;

import com.ec.sticket.models.CashItem;
import com.ec.sticket.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class UserCashPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cash_id")
    private CashItem cashItem;

    private LocalDateTime purchaseTime;

    public UserCashPurchase(User user, CashItem cashItem, LocalDateTime purchaseTime) {
        this.user = user;
        this.cashItem = cashItem;
        this.purchaseTime = purchaseTime;
    }
}
