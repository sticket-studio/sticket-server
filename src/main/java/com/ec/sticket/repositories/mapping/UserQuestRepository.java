package com.ec.sticket.repositories.mapping;

import com.ec.sticket.models.mapping.UserQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuestRepository extends JpaRepository<UserQuest, Integer> {

    List<UserQuest> findAllByUserId(int userId);

    List<UserQuest> findAllByQuestId(int questId);
}