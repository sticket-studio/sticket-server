package com.ec.sticket.models;

import com.ec.sticket.dto.request.user.UserUpdateRequest;
import com.ec.sticket.models.mapping.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserStickPurchase> userStickPurchases = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserPurchaseAsset> userPurchaseAssets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserSticonPurchase> userSticonPurchases = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserMotionticonPurchase> userMotionticonPurchases = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "following")
    private List<UserLikeUser> followers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "follower")
    private List<UserLikeUser> followings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserLikeAsset> userLikeAssets = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_like_sticon",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sticon_id",
                    referencedColumnName = "id")
    )
    private List<Sticon> likeSticons = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_like_motionticon",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "motionticon_id",
                    referencedColumnName = "id")
    )
    private List<Motionticon> likeMotionticons = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserQuest> userQuests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY
            // 판매자가 삭제돼도 구매자들을 위해 남아있어야한다.
            // 하지만 default값이 false이므로 주석처리해도 됨
            // 근데 사실 유저는 삭제되지 않을 예정.
            // 탈퇴했을 시엔 개인정보만 지우고, '탈퇴한유저'라는 값을 넣을 예정임
            /*, orphanRemoval = false*/)
    private List<Asset> sellingAssets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY
            /*, orphanRemoval = false*/)
    private List<Sticon> sellingSticons = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY
            /*, orphanRemoval = false*/)
    private List<Motionticon> sellingMotionticons = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_title",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "title_id",
                    referencedColumnName = "id")
    )
    List<Title> titles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private SnsType snsType;

    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private String name;
    private String description;
    private LocalDateTime createdTime;
    private String imgUrl;
    private int followerCnt;
    private int followingCnt;
    private int stick;

    public User() {
        createdTime = LocalDateTime.now();
        stick = 0;
    }

    public User(SnsType snsType, String email, String password, String name, String description, String imgUrl) {
        this.snsType = snsType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.createdTime = LocalDateTime.now();
        this.followerCnt = 0;
        this.followingCnt = 0;
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

    public void update(UserUpdateRequest modified) {
        if (modified.getName() != null)
            this.name = modified.getName();
        if (modified.getImgUrl() != null)
            this.imgUrl = modified.getImgUrl();
        if (modified.getDescription() != null)
            this.description = modified.getDescription();
    }

    public enum SnsType {
        NONE, FACEBOOK, KAKAOTALK, GOOGLE, NAVER
    }
}