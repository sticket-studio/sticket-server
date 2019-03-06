package com.ec.sticket.services;

import com.ec.sticket.models.Quest;
import com.ec.sticket.repositories.QuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public List<Quest> findAll(){
        return questRepository.findAll();
    }

    public Quest findById(int questId){
        Optional<Quest> questOptional = questRepository.findById(questId);
        return questOptional.orElseGet(Quest::new);
    }
}
