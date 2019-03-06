package com.ec.sticket.services;

import com.ec.sticket.models.mapping.UserQuest;
import com.ec.sticket.repositories.UserQuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQuestService {

    private final UserQuestRepository userQuestRepository;

    public UserQuestService(UserQuestRepository userQuestRepository) {
        this.userQuestRepository = userQuestRepository;
    }

    public UserQuest findById(int userQuestId) {
        Optional<UserQuest> userQuest = userQuestRepository.findById(userQuestId);
        return userQuest.orElseGet(UserQuest::new);
    }

    public List<UserQuest> findAllByUserId(int userId) {
        return userQuestRepository.findAllByUserId(userId);
    }

    public List<UserQuest> findAllByQuestId(int questId) {
        return userQuestRepository.findAllByQuestId(questId);
    }
}
