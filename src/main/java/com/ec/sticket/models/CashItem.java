package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserCashPurchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CashItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @OneToMany(mappedBy = "cashItem")
    private List<UserCashPurchase> userCashPurchaseList = new ArrayList<>();

    private int stick;
    private int cash;
}
