package com.ec.sticket.models;

import com.ec.sticket.models.mapping.MotionticonSticon;
import com.ec.sticket.models.mapping.SticonAsset;
import com.ec.sticket.models.mapping.UserSticonPurchase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Sticon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "sticon")
    private List<UserSticonPurchase> userSticonPurchases = new ArrayList<>();

    @OneToMany(mappedBy = "sticon")
    private List<MotionticonSticon> motionticonSticons = new ArrayList<>();

    @OneToMany(mappedBy = "sticon")
    private List<SticonAsset> sticonAssets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sticon_theme",
            joinColumns = @JoinColumn(name = "sticon_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id",
                    referencedColumnName = "id")
    )
    private List<Theme> themes = new ArrayList<>();

    private String name;
    private String imgUrl;
    private LocalDateTime createdTime;
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;

    public Sticon() {
        createdTime = LocalDateTime.now();
        likeCnt = 0;
        purchaseCnt = 0;
    }

    public Sticon(User author, List<SticonAsset> sticonAssets, List<Theme> themes, String name, String imgUrl, int price
            , String description) {
        this.author = author;
        this.name = name;
        this.sticonAssets = sticonAssets;
        this.themes = themes;
        this.imgUrl = imgUrl;
        this.createdTime = LocalDateTime.now();
        this.price = price;
        this.description = description;
        this.likeCnt = 0;
        this.purchaseCnt = 0;
    }

    public void setAuthor(User author) {
        if (this.author == null) {
            author.getSellingSticons().add(this);
            this.author = author;
        } else {
            throw new RuntimeException("Cannot modify author");
        }
    }
}
