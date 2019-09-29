package com.ec.sticket.models.mapping;

import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.compositekey.UserLikeUserKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserLikeUser {

    @EmbeddedId
    UserLikeUserKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("follower_id")
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("following_id")
    @JoinColumn(name = "following_id")
    private User following;

    private LocalDateTime likeTime;

    public UserLikeUser(UserLikeUserKey id) {
        this.id = id;
        this.likeTime = LocalDateTime.now();
    }
}
