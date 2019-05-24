package com.ec.sticket.models.mapping.compositekey;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Sticon;

import java.io.Serializable;

public class SticonAssetKey implements Serializable {
    private Sticon sticon;
    private Asset asset;
}