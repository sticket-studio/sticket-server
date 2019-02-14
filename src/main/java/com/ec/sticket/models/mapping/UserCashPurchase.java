package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.CashItem;
import com.ec.sticket.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCashPurchase {

    @Id
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cash_idx")
    private CashItem cashItem;

    private LocalDateTime purchaseTime;
}
