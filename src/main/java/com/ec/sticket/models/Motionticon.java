package com.ec.sticket.models;

import com.ec.sticket.exceptions.ModifyAuthorException;
import com.ec.sticket.models.mapping.MotionticonSticon;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "motionticon")
    private List<UserMotionticonPurchase> userMotionticonPurchases = new ArrayList<>();

    @OneToMany(mappedBy = "motionticon")
    private List<MotionticonSticon> motionticonSticons = new ArrayList<>();

    /*
    @OneToMany(mappedBy = "motionticon")
    private List<Sticon> sticons = new ArrayList<>();

    @OneToMany(mappedBy = "motionticon")
    private List<SticonAsset> sticonAssets = new ArrayList<>();
    */

    @Enumerated(EnumType.STRING)
    private Motion motion;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    private String name;
    private String imgUrl;
    private LocalDateTime createdTime;
    private int price;
    private String description;
    private int likeCnt;
    private int purchaseCnt;

    public Motionticon(User author, List<MotionticonSticon> motionticonSticons, Motion motion,
                       String name, Theme theme, String imgUrl, int price, String description) {
        this.author = author;
        this.motionticonSticons = motionticonSticons;
        this.motion = motion;
        this.name = name;
        this.theme = theme;
        this.imgUrl = imgUrl;
        this.createdTime = LocalDateTime.now();
        this.price = price;
        this.description = description;
        this.likeCnt = 0;
        this.purchaseCnt = 0;
    }

    public void setAuthor(User author) {
        if (this.author == null) {
            author.getSellingMotionticons().add(this);
            this.author = author;
        } else {
            throw new ModifyAuthorException("Cannot modify author");
        }
    }

    public enum Motion {
        MOTION_OPEN_MOUTH, MOTION_CLOSE_EYE
    }
}
