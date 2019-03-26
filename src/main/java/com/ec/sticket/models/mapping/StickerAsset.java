package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.mapping.compositekey.MotionticonStickerKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@IdClass(value= MotionticonStickerKey.class)
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
}
