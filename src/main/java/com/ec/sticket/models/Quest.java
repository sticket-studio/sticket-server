package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserQuest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "quest")
    private List<UserQuest> userQuests= new ArrayList<>();

    private String subject;
    private String description;
    private int rewardStick;

    public Quest(String subject, String description, int rewardStick) {
        this.subject = subject;
        this.description = description;
        this.rewardStick = rewardStick;
    }
}
