package com.ec.sticket.dto.response.user;

import com.ec.sticket.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class UserSimple {
    private int id;
    private String name;
    private String email;
    private String imgUrl;
    private String description;
    private int worksCnt;
    private int followerCnt;

    public static UserSimple mapping(User user){
        return UserSimple.builder()
                .id(user.getId())
                .imgUrl(user.getImgUrl())
                .description(user.getDescription())
                .name(user.getName())
                .email(user.getEmail())
                .followerCnt(user.getFollowerCnt())
                .worksCnt(user.getSellingAssets().size())
                .build();
    }
}
