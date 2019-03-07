package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserMotionticonPurchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Motionticon {
    public enum Motion {
        MOTION_OPEN_MOUTH, MOTION_CLOSE_EYE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "motionticon")
    private List<UserMotionticonPurchase> userMotionticonPurchases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "motionticon_sticker",
            joinColumns = @JoinColumn(name = "motionticon_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sticker_id",
                    referencedColumnName = "id")
    )
    private List<Sticker> stickers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Motion motion;

    @ManyToMany
    @JoinTable(name = "motionticon_theme",
            joinColumns = @JoinColumn(name = "motionticon_id",
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

    public Motionticon(User author, List<Sticker> stickers, Motion motion, String name, List<Theme> themes, String imgUrl
            , int price, String description, int likeCnt, int purchaseCnt) {
        this.author = author;
        this.stickers = stickers;
        this.motion = motion;
        this.name = name;
        this.themes = themes;
        this.imgUrl = imgUrl;
        this.createdTime = LocalDateTime.now();
        this.price = price;
        this.description = description;
        this.likeCnt = likeCnt;
        this.purchaseCnt = purchaseCnt;
    }

    public void setAuthor(User author) {
        if (this.author == null) {
            author.getSellingMotionticons().add(this);
            this.author = author;
        } else {
            throw new RuntimeException("Cannot modify author");
        }
    }
}
