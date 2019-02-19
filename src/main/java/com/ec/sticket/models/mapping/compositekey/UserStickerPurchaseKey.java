package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.User;

import java.io.Serializable;

public class UserStickerPurchaseKey implements Serializable {
    private User user;
    private Sticker sticker;
}