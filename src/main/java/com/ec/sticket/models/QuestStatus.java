package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserQuest;
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
public class QuestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @OneToMany(mappedBy = "questStatus")
    private List<UserQuest> userQuests = new ArrayList<>();

    private String status;
}
