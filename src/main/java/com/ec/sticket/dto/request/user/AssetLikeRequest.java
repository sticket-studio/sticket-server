package com.ec.sticket.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetLikeRequest {
    private int assetId;
    private int userId;
}
