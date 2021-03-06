package com.ec.sticket.services.mapping;

import com.ec.sticket.models.Quest;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserQuest;
import com.ec.sticket.repositories.mapping.UserQuestRepository;
import com.ec.sticket.util.ApiMessage;
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

    public ApiMessage save(User user, Quest quest) {
        if (user != null && quest != null) {
            userQuestRepository.save(new UserQuest(user, quest));
            return new ApiMessage(ApiMessage.Status.SUCCESS);
        } else {
            return new ApiMessage(ApiMessage.Status.FAIL);
        }
    }
}
