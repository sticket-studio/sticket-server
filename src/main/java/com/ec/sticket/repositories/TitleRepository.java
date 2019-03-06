package com.ec.sticket.repositories;

import com.ec.sticket.models.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    @Query("SELECT t FROM Title t INNER JOIN t.users u WHERE u.id= :userId")
    List<Title> findAllByUserId(@Param("userId") int userId);
}