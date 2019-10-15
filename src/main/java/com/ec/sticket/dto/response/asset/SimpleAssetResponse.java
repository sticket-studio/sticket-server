package com.ec.sticket.dto.response.asset;

import com.ec.sticket.models.Asset;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SimpleAssetResponse {
    private int id;
    private String imgUrl;
    private String name;
    private Asset.Landmark landmark;
}
