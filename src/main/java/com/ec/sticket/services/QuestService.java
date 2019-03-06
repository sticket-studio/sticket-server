package com.ec.sticket.services;

import com.ec.sticket.models.Quest;
import com.ec.sticket.repositories.QuestRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public ApiMessage save(Quest quest) {
        if (quest.getSubject() != null && quest.getDescription() != null) {
            questRepository.save(quest);
            return new ApiMessage(ApiMessage.Status.SUCCESS);
        } else {
            return new ApiMessage(ApiMessage.Status.FAIL);
        }
    }

    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    public Quest findById(int questId) {
        Optional<Quest> questOptional = questRepository.findById(questId);
        return questOptional.orElseGet(Quest::new);
    }
}
