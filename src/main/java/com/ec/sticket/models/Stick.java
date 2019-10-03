package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserStickPurchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Stick {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "stick")
    @JsonIgnore
    private List<UserStickPurchase> userStickPurchaseList = new ArrayList<>();

    private int stick;
    private int cash;
    private String imgUrl;

    public Stick(int stick, int cash, String imgUrl) {
        this.stick = stick;
        this.cash = cash;
        this.imgUrl = imgUrl;
    }
}
