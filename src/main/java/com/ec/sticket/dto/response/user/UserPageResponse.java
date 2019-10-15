package com.ec.sticket.dto.response.user;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class UserPageResponse {
    private String imgUrl;
    private String name;
    private String email;
    private String description;
    private int followerCnt;
    private int followingCnt;
    private int worksCnt;
    private int stick;
    private LocalDateTime createdTime;
    private List<Asset> assets;

    public static UserPageResponse mapping(User user){
        return UserPageResponse.builder()
                .imgUrl(user.getImgUrl())
                .description(user.getDescription())
                .createdTime(user.getCreatedTime())
                .name(user.getName())
                .email(user.getEmail())
                .stick(user.getStick())
                .followerCnt(user.getFollowerCnt())
                .followingCnt(user.getFollowingCnt())
                .worksCnt(user.getSellingAssets().size())
                .assets(user.getSellingAssets())
                .build();
    }
}
