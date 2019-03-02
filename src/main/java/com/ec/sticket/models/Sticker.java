package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserStickerPurchase;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "sticker")
    private List<UserStickerPurchase> userStickerPurchases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticker_asset",
            joinColumns = @JoinColumn(name = "sticker_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id",
                    referencedColumnName = "id")
    )
    private List<Asset> assets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "motionticon_sticker",
            joinColumns = @JoinColumn(name = "sticker_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "motionticon_id",
                    referencedColumnName = "id")
    )
    private List<Asset> motionticons = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticker_theme",
            joinColumns = @JoinColumn(name = "sticker_id",
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

    public Sticker(User author, List<Asset> assets, List<Theme> themes, String imgUrl, LocalDateTime createdTime
            , int price, String description, int likeCnt, int purchaseCnt) {
        this.author = author;
        this.userStickerPurchases = userStickerPurchases;
        this.assets = assets;
        this.themes = themes;
        this.imgUrl = imgUrl;
        this.createdTime = createdTime;
        this.price = price;
        this.description = description;
        this.likeCnt = likeCnt;
        this.purchaseCnt = purchaseCnt;
    }
}
