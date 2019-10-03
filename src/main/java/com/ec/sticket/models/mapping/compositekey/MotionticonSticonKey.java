package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.models.Sticon;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class MotionticonSticonKey implements Serializable {
    private Motionticon motionticon;
    private Sticon sticon;
}