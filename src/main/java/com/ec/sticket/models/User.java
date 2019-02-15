package com.ec.sticket.models;

import com.ec.sticket.models.mapping.UserAssetPurchase;
import com.ec.sticket.models.mapping.UserCashPurchase;
import com.ec.sticket.models.mapping.UserQuest;
import com.ec.sticket.models.mapping.UserStickerPurchase;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idx;

    @OneToMany(mappedBy = "user")
    private List<UserCashPurchase> userCashPurchases= new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserStickerPurchase> userStickerPurchases= new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserAssetPurchase> userAssetPurchases= new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserQuest> quests= new ArrayList<>();

    @OneToMany(mappedBy = "author"
            // 판매자가 삭제돼도 구매자들을 위해 남아있어야한다.
            // 하지만 default값이 false이므로 주석처리해도 됨
            // 근데 사실 유저는 삭제되지 않을 예정.
            // 탈퇴했을 시엔 개인정보만 지우고, '탈퇴한유저'라는 값을 넣을 예정임
            /*, orphanRemoval = false*/ )
    private List<Sticker> sellingStickers= new ArrayList<>();

    @OneToMany(mappedBy = "author"
            /*, orphanRemoval = false*/ )
    private List<Asset> sellingAssets= new ArrayList<>();

    private String id;
    private String pw;
    private String name;
    private String email;
    private String snsType;
    private String token;
    private int stick;
}
