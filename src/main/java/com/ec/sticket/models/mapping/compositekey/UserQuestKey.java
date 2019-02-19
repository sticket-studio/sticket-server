package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Quest;
import com.ec.sticket.models.User;

import java.io.Serializable;

public class UserQuestKey implements Serializable {
    private User user;
    private Quest quest;
}