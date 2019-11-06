package com.ec.sticket.dto.request.asset;

import com.ec.sticket.models.Asset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
public class InsertAssetRequest {

    private MultipartFile img;
    private String description;
    private Asset.Landmark landmark;
    private String name;
    private int price = Asset.DEFAULT_PRICE;
    private int themeId;

    public Asset toAsset(){
        return Asset.builder()
                .description(this.description)
                .landmark(this.landmark)
                .name(this.name)
                .price(this.price)
                .createdTime(LocalDateTime.now())
                .likeCnt(0)
                .purchaseCnt(0)
                .build();
    }
}
