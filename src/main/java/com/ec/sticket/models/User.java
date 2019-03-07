package com.ec.sticket.models;

import com.ec.sticket.models.mapping.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
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
    private List<UserStickerPurchase> userStickerPurchases = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserMotionticonPurchase> userMotionticonPurchases = new ArrayList<>();

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
    private List<Sticker> sellingStickers = new ArrayList<>();

    @OneToMany(mappedBy = "author"
            /*, orphanRemoval = false*/)
    @JsonIgnore
    private List<Asset> sellingAssets = new ArrayList<>();

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

    private String email;
    private String pw;
    private String name;
    private String snsType;
    private String token;
    private String imgUrl;
    private int stick;

    public User(String email, String pw, String name, String snsType, String token, String imgUrl) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.snsType = snsType;
        this.token = token;
        this.imgUrl = imgUrl;
        this.stick = 0;
    }

    public void addSellingAsset(Asset asset) {

        // 양방향 매핑을 위해 이것도 해줘야하지만, 어차피 Asset 생성자에 author까지 넣을 예정이므로 필요 없을 듯
        // asset.setAuthor(this);

        if (asset.getAuthor() == null) {
//            throw new RuntimeException("Need to set author. Use AllArgsConstructor.");
        } else if (asset.getAuthor() != this) {
//            throw new RuntimeException("The sticker isn't this user's own.");
        } else {
            sellingAssets.add(asset);
        }
    }

    public void addSellingSticker(Sticker sticker) {

        // sticker.setAuthor(this);

        if (sticker.getAuthor() == null) {
//            throw new RuntimeException("Need to set author. Use AllArgsConstructor.");
        } else if (sticker.getAuthor() != this) {
//            throw new RuntimeException("The sticker isn't this user's own.");
        } else {
            sellingStickers.add(sticker);
        }
    }

    public void addSellingMotionticon(Motionticon motionticon) {

        // motionticon.setAuthor(this);

        if (motionticon.getAuthor() == null) {
//            throw new RuntimeException("Need to set author. Use AllArgsConstructor.");
        } else if (motionticon.getAuthor() != this) {
//            throw new RuntimeException("The sticker isn't this user's own.");
        } else {
            sellingMotionticons.add(motionticon);
        }
    }

    public void update(User modified){
        this.email = modified.getEmail();
        this.pw = modified.getPw();
        this.name = modified.getName();
        this.imgUrl = modified.getImgUrl();
    }
}