package com.ec.sticket.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "asset_theme",
            joinColumns = @JoinColumn(name = "theme_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id",
                    referencedColumnName = "id")
    )
    private List<Asset> assets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticker_theme",
            joinColumns = @JoinColumn(name = "theme_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sticker_id",
                    referencedColumnName = "id")
    )
    private List<Sticker> stickers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "motionticon_theme",
            joinColumns = @JoinColumn(name = "theme_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "motionticon_id",
                    referencedColumnName = "id")
    )
    private List<Motionticon> motionticons = new ArrayList<>();

    private String name;
    private int cnt;

    public Theme(String name, int cnt) {
        this.name = name;
        this.cnt = cnt;
    }
}
