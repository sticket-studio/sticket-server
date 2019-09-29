package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class UserLikeAssetKey implements Serializable {
    private User user;
    private Asset asset;
}