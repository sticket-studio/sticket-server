package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserAssetPurchase;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "asset")
    private List<UserAssetPurchase> userAssetPurchases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticker_asset",
            joinColumns = @JoinColumn(name = "asset_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sticker_id",
                    referencedColumnName = "id")
    )
    private List<Sticker> stickers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "asset_theme",
            joinColumns = @JoinColumn(name = "asset_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id",
                    referencedColumnName = "id")
    )
    private List<Theme> themes = new ArrayList<>();

    private String imgUrl;
    private LocalDateTime createdTime;
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;
    @Enumerated(value = EnumType.STRING)
    private Landmark landmark;

    public Asset(User author, Landmark landmark, List<Theme> themes, String imgUrl, LocalDateTime createdTime
            , int price, String description, int likeCnt, int purchaseCnt) {
        this.author = author;
        this.landmark = landmark;
        this.themes = themes;
        this.imgUrl = imgUrl;
        this.createdTime = createdTime;
        this.price = price;
        this.description = description;
        this.likeCnt = likeCnt;
        this.purchaseCnt = purchaseCnt;
    }

    public enum Landmark{
        EYE_LEFT, EYE_RIGHT, NOSE, MOUTH_LEFT, MOUTH_RIGHT, MOUTH_BOTTOM, CHEEK_LEFT, CHEEK_RIGHT
    }
}
