package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.mapping.compositekey.SticonAssetKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@IdClass(value = SticonAssetKey.class)
public class SticonAsset {

    @Id
    @ManyToOne
    @JoinColumn(name = "sticon_id")
    private Sticon sticon;

    @Id
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private double offsetX;
    private double offsetY;
    private int rotate;
    private boolean flip;

    public SticonAsset(Sticon sticon, Asset asset, double offsetX, double offsetY, int rotate, boolean flip) {
        this.sticon = sticon;
        this.asset = asset;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.rotate = rotate;
        this.flip = flip;
    }
}
