package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.models.Sticker;

import java.io.Serializable;

public class MotionticonStickerKey implements Serializable {
    private Motionticon motionticon;
    private Sticker sticker;
}