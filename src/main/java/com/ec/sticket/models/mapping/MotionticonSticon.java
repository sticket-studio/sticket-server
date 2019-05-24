package com.ec.sticket.models.mapping;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.mapping.compositekey.MotionticonSticonKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@IdClass(value= MotionticonSticonKey.class)
public class MotionticonSticon {

    @Id
    @ManyToOne
    @JoinColumn(name = "motionticon_id")
    private Motionticon motionticon;

    @Id
    @ManyToOne
    @JoinColumn(name = "sticon_id")
    private Sticon sticon;

    private int sequenceNum;
    private int maintainingTime;
}
