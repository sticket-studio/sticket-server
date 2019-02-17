package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserAssetPurchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Asset {

    @Id
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "author_idx")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_idx")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "landmark_idx")
    private Landmark landmark;

    @OneToMany(mappedBy = "asset")
    private List<UserAssetPurchase> userAssetPurchases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticker_asset",
            joinColumns = @JoinColumn(name = "asset_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "sticker_idx",
                    referencedColumnName = "idx")
    )
    private List<Sticker> stickers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "asset_hashtag",
            joinColumns = @JoinColumn(name = "asset_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_idx",
                    referencedColumnName = "idx")
    )
    private List<Hashtag> hashtags = new ArrayList<>();

    private String imgUrl;
    private LocalDateTime createdTime;
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;
}
