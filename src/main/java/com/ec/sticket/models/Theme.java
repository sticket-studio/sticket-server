package com.ec.sticket.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
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
    @JoinTable(name = "sticon_theme",
            joinColumns = @JoinColumn(name = "theme_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sticon_id",
                    referencedColumnName = "id")
    )
    private List<Sticon> sticons = new ArrayList<>();

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

    public Theme(String name) {
        this.name = name;
        this.cnt = 0;
    }
}
