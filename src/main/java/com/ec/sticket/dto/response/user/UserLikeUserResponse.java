package com.ec.sticket.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserLikeUserResponse {
    private int followerId;
    private int followingId;
    private boolean liked;
}
