package com.ec.sticket.models;

import java.util.List;

public class Motionticon {
    public enum Motion {
        MOTION_OPEN_MOUTH, MOTION_CLOSE_EYE
    }


    private List<Sticker> stickers;
    private Motion motion;
}
