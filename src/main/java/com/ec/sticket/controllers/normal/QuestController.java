package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Quest;
import com.ec.sticket.services.QuestService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/quest")
public class QuestController {

    private final QuestService questService;
    private final UserService userService;

    public QuestController(QuestService questService, UserService userService) {
        this.questService = questService;
        this.userService = userService;
    }

    @GetMapping("")
    public List<Quest> findAllQuests(){
        return questService.findAll();
    }

    @GetMapping("/{questId}")
    public Quest findQuestById(@PathVariable("questId") int questId){
        return questService.findById(questId);
    }

    @PostMapping("")
    public ApiMessage saveQuest(@RequestBody Quest quest){
        return questService.save(quest);
    }

    @PutMapping("")
    public ApiMessage updateQuest(@RequestBody Quest quest){
        return questService.update(quest);
    }

    @DeleteMapping("/{questId}")
    public ApiMessage deleteQuest(@PathVariable("questId") int questId){
        return questService.delete(questId);
    }
}
