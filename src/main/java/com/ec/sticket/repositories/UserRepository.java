package com.ec.sticket.repositories;

import com.ec.sticket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query(value = "SELECT u FROM user u INNER JOIN sticker s ON u.id = sa.asset_id WHERE sa.sticker_id = :stickerid")
//    List<User> findByStickerid(@Param("stickerid") int stickerid);

}