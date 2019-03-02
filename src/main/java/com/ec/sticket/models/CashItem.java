package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserCashPurchase;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class CashItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "cashItem")
    private List<UserCashPurchase> userCashPurchaseList = new ArrayList<>();

    private int stick;
    private int cash;

    public CashItem(int stick, int cash) {
        this.stick = stick;
        this.cash = cash;
    }
}
