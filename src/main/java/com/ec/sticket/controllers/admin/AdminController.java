package com.ec.sticket.controllers.admin;

import com.ec.sticket.models.Quest;
import com.ec.sticket.services.QuestService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final QuestService questService;

    public AdminController(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping("/quest")
    public ApiMessage saveQuest(@RequestBody Quest quest){
        return questService.save(quest);
    }
}
