package com.ec.sticket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sticker {

    @Id
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToMany
    @JoinTable(name = "sticker_has_assets",
            joinColumns = @JoinColumn(name = "sticker_idx"),
            inverseJoinColumns = @JoinColumn(name = "asset_idx")
    )
    private List<Asset> assets;

    @ManyToMany
    @JoinTable(name = "sticker_hashtag",
            joinColumns = @JoinColumn(name = "sticker_idx"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_idx")
    )
    private List<Hashtag> hashtag;

    private String imgUrl;
    private LocalDateTime createdTime;
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;
}
