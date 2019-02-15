package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Quest;
import com.ec.sticket.models.QuestStatus;
import com.ec.sticket.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserQuest {

    @ManyToOne
    @JoinColumn(name = "user_idx"
            , referencedColumnName = "idx")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quest_idx"
            , referencedColumnName = "idx")
    private Quest quest;

    @ManyToOne
    @JoinColumn(name = "quest_status_idx"
            , referencedColumnName = "idx")
    private QuestStatus questStatus;
}
