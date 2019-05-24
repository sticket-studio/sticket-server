package com.ec.sticket.models;

import com.ec.sticket.models.mapping.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserCashItemPurchase> userCashItemPurchases = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserAssetPurchase> userAssetPurchases = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserSticonPurchase> userSticonPurchases = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserMotionticonPurchase> userMotionticonPurchases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_like_asset",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id",
                    referencedColumnName = "id")
    )
    @JsonIgnore
    private List<Asset> likeAssets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_like_sticon",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sticon_id",
                    referencedColumnName = "id")
    )
    @JsonIgnore
    private List<Sticon> likeSticons = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_like_montionticon",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "montionticon_id",
                    referencedColumnName = "id")
    )
    @JsonIgnore
    private List<Motionticon> likeMotionticons = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserQuest> userQuests = new ArrayList<>();

    @OneToMany(mappedBy = "author"
            // 판매자가 삭제돼도 구매자들을 위해 남아있어야한다.
            // 하지만 default값이 false이므로 주석처리해도 됨
            // 근데 사실 유저는 삭제되지 않을 예정.
            // 탈퇴했을 시엔 개인정보만 지우고, '탈퇴한유저'라는 값을 넣을 예정임
            /*, orphanRemoval = false*/)
    @JsonIgnore
    private List<Asset> sellingAssets = new ArrayList<>();

    @OneToMany(mappedBy = "author"
            /*, orphanRemoval = false*/)
    @JsonIgnore
    private List<Sticon> sellingSticons = new ArrayList<>();

    @OneToMany(mappedBy = "author"
            /*, orphanRemoval = false*/)
    @JsonIgnore
    private List<Motionticon> sellingMotionticons = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_title",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "title_id",
                    referencedColumnName = "id")
    )
    @JsonIgnore
    List<Title> titles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private SnsType snsType;

    private String email;
    @JsonIgnore
    private String password;
    private String name;
    private LocalDateTime createdTime;
    private String imgUrl;
    private int stick;

    public User() {
        createdTime = LocalDateTime.now();
        stick = 0;
    }

    public User(SnsType snsType, String email, String password, String name, String imgUrl) {
        this.snsType = snsType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.imgUrl = imgUrl;
        this.createdTime = LocalDateTime.now();
        this.stick = 0;
    }

    public void addSellingAsset(Asset asset) {

        // 양방향 매핑을 위해 이것도 해줘야하지만, 어차피 Asset 생성자에 author까지 넣을 예정이므로 필요 없을 듯
        // asset.setAuthor(this);

        if (asset.getAuthor() == null) {
//            throw new RuntimeException("Need to set author. Use AllArgsConstructor.");
        } else if (asset.getAuthor() != this) {
//            throw new RuntimeException("The sticon isn't this user's own.");
        } else {
            sellingAssets.add(asset);
        }
    }

    public void addSellingSticon(Sticon sticon) {

        // sticon.setAuthor(this);

        if (sticon.getAuthor() == null) {
//            throw new RuntimeException("Need to set author. Use AllArgsConstructor.");
        } else if (sticon.getAuthor() != this) {
//            throw new RuntimeException("The sticon isn't this user's own.");
        } else {
            sellingSticons.add(sticon);
        }
    }

    public void addSellingMotionticon(Motionticon motionticon) {

        // motionticon.setAuthor(this);

        if (motionticon.getAuthor() == null) {
//            throw new RuntimeException("Need to set author. Use AllArgsConstructor.");
        } else if (motionticon.getAuthor() != this) {
//            throw new RuntimeException("The sticon isn't this user's own.");
        } else {
            sellingMotionticons.add(motionticon);
        }
    }

    public void update(User modified) {
        this.email = modified.getEmail();
        this.password = modified.getPassword();
        this.name = modified.getName();
        this.imgUrl = modified.getImgUrl();
    }

    public enum SnsType {
        NONE, FACEBOOK, KAKAOTALK, GOOGLE, NAVER
    }
}