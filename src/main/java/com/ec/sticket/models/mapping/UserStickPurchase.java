package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Stick;
import com.ec.sticket.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
public class UserStickPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cash_id")
    private Stick stick;

    private LocalDateTime purchaseTime;

    public UserStickPurchase(User user, Stick stick) {
        this.user = user;
        this.stick = stick;
        this.purchaseTime = LocalDateTime.now();
    }
}
