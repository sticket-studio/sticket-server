package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Quest;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserQuestKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@IdClass(value = UserQuestKey.class)
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

    QuestStatus questStatus;

    public UserQuest(User user, Quest quest) {
        this.user = user;
        this.quest = quest;
        this.questStatus = QuestStatus.DOING;

        user.getUserQuests().add(this);
        quest.getUserQuests().add(this);
    }

    public enum QuestStatus {FAIL, DOING, DONE}
}
