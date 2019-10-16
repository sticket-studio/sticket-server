package com.ec.sticket.dto.response.user;

import com.ec.sticket.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SimpleUserResponse {
    private int id;
    private String name;
    private String email;
    private String imgUrl;
    private String description;
    private int worksCnt;
    private int followerCnt;
    private int followingCnt;

    public static SimpleUserResponse mapping(User user){
        return SimpleUserResponse.builder()
                .id(user.getId())
                .imgUrl(user.getImgUrl())
                .description(user.getDescription())
                .name(user.getName())
                .email(user.getEmail())
                .followerCnt(user.getFollowerCnt())
                .followingCnt(user.getFollowingCnt())
                .worksCnt(user.getSellingAssets().size())
                .build();
    }
}
