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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idx;

    @ManyToMany
    @JoinTable(name = "sticker_hashtag",
            joinColumns = @JoinColumn(name = "hashtag_idx"),
            inverseJoinColumns = @JoinColumn(name = "sticker_idx")
    )
    private List<Sticker> stickers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "asset_hashtag",
            joinColumns = @JoinColumn(name = "hashtag_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "asset_idx",
                    referencedColumnName = "idx")
    )
    private List<Asset> assets = new ArrayList<>();

    private String name;
    private int cnt;
}
