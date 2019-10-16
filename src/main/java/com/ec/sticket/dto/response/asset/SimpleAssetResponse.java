package com.ec.sticket.dto.response.asset;

import com.ec.sticket.models.Asset;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class SimpleAssetResponse {
    private int id;
    private String imgUrl;
    private String name;
    private Asset.Landmark landmark;
    private int authorId;
    private String authorName;
    private int themeId;
    private String themeName;
    private int price;

    public static SimpleAssetResponse mapping(Asset asset){
        return SimpleAssetResponse.builder()
                .id(asset.getId())
                .imgUrl(asset.getImgUrl())
                .name(asset.getName())
                .landmark(asset.getLandmark())
                .authorId(asset.getAuthor().getId())
                .authorName(asset.getAuthor().getName())
                .themeId(asset.getTheme().getId())
                .themeName(asset.getTheme().getName())
                .price(asset.getPrice())
                .build();
    }
}
