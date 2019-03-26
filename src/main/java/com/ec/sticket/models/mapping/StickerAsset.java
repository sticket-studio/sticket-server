package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.mapping.compositekey.MotionticonStickerKey;
import com.ec.sticket.models.mapping.compositekey.StickerAssetKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@IdClass(value = StickerAssetKey.class)
public class StickerAsset {

    @Id
    @ManyToOne
    @JoinColumn(name = "sticker_id")
    private Sticker sticker;

    @Id
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private double offsetX;
    private double offsetY;
    private int rotate;
    private boolean flip;

    public StickerAsset(Sticker sticker, Asset asset, double offsetX, double offsetY, int rotate, boolean flip) {
        this.sticker = sticker;
        this.asset = asset;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.rotate = rotate;
        this.flip = flip;
    }
}
