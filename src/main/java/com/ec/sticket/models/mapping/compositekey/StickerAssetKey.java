package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Sticker;

import java.io.Serializable;

public class StickerAssetKey implements Serializable {
    private Sticker sticker;
    private Asset asset;
}