package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.models.User;

import java.io.Serializable;

public class UserMotionticonPurchaseKey implements Serializable {
    private User user;
    private Motionticon motionticon;
}