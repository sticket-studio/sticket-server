package com.ec.sticket.repositories.mapping.like;

import com.ec.sticket.models.mapping.UserLikeUser;
import com.ec.sticket.models.mapping.compositekey.UserLikeUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLikeUserRepository extends JpaRepository<UserLikeUser, UserLikeUserKey> {
    List<UserLikeUser> findAllByFollowerId(Integer followerId);
}
