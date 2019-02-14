package com.ec.sticket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hashtag {
    @Id
    private Long idx;

    @ManyToMany
    @JoinTable(name = "sticker_has_hashtag",
            joinColumns = @JoinColumn(name = "hashtag_idx"),
            inverseJoinColumns = @JoinColumn(name = "sticker_idx")
    )
    private List<Sticker> stickers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "asset_has_hashtag",
            joinColumns = @JoinColumn(name = "hashtag_idx"),
            inverseJoinColumns = @JoinColumn(name = "asset_idx")
    )
    private List<Asset> assets = new ArrayList<>();

    private String name;
    private int cnt;
}
