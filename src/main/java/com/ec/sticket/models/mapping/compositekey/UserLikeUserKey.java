package com.ec.sticket.models.mapping.compositekey;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class UserLikeUserKey implements Serializable {
    @Column(name = "follower_id")
    private int followerId;
    @Column(name = "following_id")
    private int followingId;
}