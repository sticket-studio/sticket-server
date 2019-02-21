package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserMotionticonPurchase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Motionticon {
    public enum Motion {
        MOTION_OPEN_MOUTH, MOTION_CLOSE_EYE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "author_idx")
    private User author;

    @OneToMany(mappedBy = "motionticon")
    private List<UserMotionticonPurchase> userMotionticonPurchases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "motionticon_sticker",
            joinColumns = @JoinColumn(name = "motionticon_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "sticker_idx",
                    referencedColumnName = "idx")
    )
    private List<Sticker> stickers = new ArrayList<>();

    private Motion motion;

    @ManyToMany
    @JoinTable(name = "motionticon_theme",
            joinColumns = @JoinColumn(name = "motionticon_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "theme_idx",
                    referencedColumnName = "idx")
    )
    private List<Theme> themes = new ArrayList<>();

    private String imgUrl;
    private LocalDateTime createdTime;
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;
}
