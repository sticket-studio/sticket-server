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
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idx;

    @ManyToMany
    @JoinTable(name = "asset_theme",
            joinColumns = @JoinColumn(name = "theme_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "asset_idx",
                    referencedColumnName = "idx")
    )
    private List<Asset> assets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticker_theme",
            joinColumns = @JoinColumn(name = "theme_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "sticker_idx",
                    referencedColumnName = "idx")
    )
    private List<Sticker> stickers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "motionticon_theme",
            joinColumns = @JoinColumn(name = "theme_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "motionticon_idx",
                    referencedColumnName = "idx")
    )
    private List<Motionticon> motionticons = new ArrayList<>();

    private String name;
    private int cnt;
}
