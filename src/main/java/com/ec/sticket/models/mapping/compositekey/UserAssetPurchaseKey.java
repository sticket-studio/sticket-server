package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;

import java.io.Serializable;

public class UserAssetPurchaseKey implements Serializable {
    private User user;
    private Asset asset;
}