package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Motionticon;
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
public class MotionticonSticker {

    @Id
    @ManyToOne
    @JoinColumn(name = "motionticon_id")
    private Motionticon motionticon;

    @Id
    @ManyToOne
    @JoinColumn(name = "sticker_id")
    private Sticker sticker;

    private int sequenceNum;
    private int maintainingTime;
}
