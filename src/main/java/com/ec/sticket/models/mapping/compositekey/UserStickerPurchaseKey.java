package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.User;

import java.io.Serializable;

public class UserSticonPurchaseKey implements Serializable {
    private User user;
    private Sticon sticon;
}