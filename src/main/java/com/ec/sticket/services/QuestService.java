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

    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    public Quest findById(int questId) {
        Optional<Quest> quest = questRepository.findById(questId);
        return quest.orElseGet(Quest::new);
    }

    public ApiMessage save(Quest quest) {
        if (quest != null && quest.getSubject() != null) {
            questRepository.save(quest);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(Quest modified) {
        Optional<Quest> questOptional = questRepository.findById(modified.getId());
        if (questOptional.isPresent()) {
            Quest quest = questOptional.get();

            quest.setSubject(modified.getSubject());
            quest.setDescription(modified.getDescription());
            quest.setRewardStick(modified.getRewardStick());

            questRepository.save(quest);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int questId) {
        Optional<Quest> quest = questRepository.findById(questId);
        if (quest.isPresent()) {
            questRepository.deleteById(questId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
}
