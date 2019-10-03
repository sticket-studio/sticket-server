package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Quest;
import com.ec.sticket.models.User;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class UserQuestKey implements Serializable {
    private User user;
    private Quest quest;
}