package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Quest;
import com.ec.sticket.models.QuestStatus;
import com.ec.sticket.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserQuest {
    private User user;
    private Quest quest;
    private QuestStatus questStatus;
}
