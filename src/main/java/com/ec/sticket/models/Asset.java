package com.ec.sticket.models;

import com.ec.sticket.exceptions.ModifyAuthorException;
import com.ec.sticket.models.mapping.SticonAsset;
import com.ec.sticket.models.mapping.UserLikeAsset;
import com.ec.sticket.models.mapping.UserPurchaseAsset;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ApiModel(description = "asset")
public class Asset implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "AssetGenerator")
    @ApiModelProperty(notes = "Id for Asset")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @JsonIgnore
    @OneToMany(mappedBy = "asset")
    private List<UserPurchaseAsset> userPurchaseAssets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "asset")
    private List<SticonAsset> sticonAssets = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @JsonIgnore
    @OneToMany(mappedBy = "asset")
    private List<UserLikeAsset> userLikeAssets = new ArrayList<>();

    @ApiModelProperty(notes = "name for Asset", example = "에셋이름!!")
    private String name;
    private String imgUrl;
    private LocalDateTime createdTime;
    @ApiModelProperty(notes = "price for Asset", example = "30")
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;
    @Enumerated(value = EnumType.STRING)
    private Landmark landmark;

    public Asset() {
        createdTime = LocalDateTime.now();
        likeCnt = 0;
        purchaseCnt = 0;
    }

    public Asset(User author, Landmark landmark, Theme theme, String name, String imgUrl, int price
            , String description) {
        this.author = author;
        this.landmark = landmark;
        this.theme = theme;
        this.name = name;
        this.imgUrl = imgUrl;
        this.createdTime = LocalDateTime.now();
        this.price = price;
        this.description = description;
        this.likeCnt = 0;
        this.purchaseCnt = 0;
    }

    public void setAuthor(User author) {
        if (this.author == null) {
            author.getSellingAssets().add(this);
            this.author = author;
        } else {
            throw new ModifyAuthorException("Cannot modify author");
        }
    }

    public enum Landmark {
        EYE_LEFT, EYE_RIGHT, NOSE, MOUTH_LEFT, MOUTH_RIGHT, MOUTH_BOTTOM,
        CHEEK_LEFT, CHEEK_RIGHT, EAR_LEFT, EAR_RIGHT
    }
}
