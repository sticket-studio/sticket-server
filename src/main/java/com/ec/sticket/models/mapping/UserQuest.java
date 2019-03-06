package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Quest;
import com.ec.sticket.models.QuestStatus;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserQuestKey;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@IdClass(value= UserQuestKey.class)
public class UserQuest {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id"
            , referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "quest_id"
            , referencedColumnName = "id")
    private Quest quest;

    @ManyToOne
    @JoinColumn(name = "quest_status_id"
            , referencedColumnName = "id")
    private QuestStatus questStatus;

    public UserQuest(User user, Quest quest, QuestStatus questStatus) {
        this.user = user;
        this.quest = quest;
        this.questStatus = questStatus;

        user.getUserQuests().add(this);
        quest.getUserQuests().add(this);
    }

    public void setQuestStatus(QuestStatus questStatus) {
        this.questStatus = questStatus;
    }
}
