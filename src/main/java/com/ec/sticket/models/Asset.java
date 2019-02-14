package com.ec.sticket.models;

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
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "author_idx")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_idx")
    private Category category;

    @ManyToMany
    @JoinTable(name = "sticker_has_assets",
            joinColumns = @JoinColumn(name = "asset_idx"),
            inverseJoinColumns = @JoinColumn(name = "sticker_idx")
    )
    private List<Sticker> stickers;

    @ManyToMany
    @JoinTable(name = "asset_hashtag",
            joinColumns = @JoinColumn(name = "asset_idx"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_idx")
    )
    private List<Hashtag> hashtag = new ArrayList<>();

    private Landmark landmark;
    private String imgUrl;
    private LocalDateTime createdTime;
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;
}
