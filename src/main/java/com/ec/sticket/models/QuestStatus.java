package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserQuest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class QuestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "questStatus")
    private List<UserQuest> userQuests = new ArrayList<>();

    private String status;

    public QuestStatus(String status) {
        this.status = status;
    }
}
