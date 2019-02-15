package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserStickerPurchase;
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
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "author_idx")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_idx")
    private Category category;

    @OneToMany(mappedBy = "sticker")
    private List<UserStickerPurchase> userStickerPurchases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticker_asset",
            joinColumns = @JoinColumn(name = "sticker_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "asset_idx",
                    referencedColumnName = "idx")
    )
    private List<Asset> assets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticker_hashtag",
            joinColumns = @JoinColumn(name = "sticker_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_idx",
                    referencedColumnName = "idx")
    )
    private List<Hashtag> hashtag = new ArrayList<>();

    private String imgUrl;
    private LocalDateTime createdTime;
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;
}
