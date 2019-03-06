package com.ec.sticket.controllers.admin;

import com.ec.sticket.models.Quest;
import com.ec.sticket.models.Theme;
import com.ec.sticket.services.QuestService;
import com.ec.sticket.services.ThemeService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final QuestService questService;
    private final ThemeService themeService;

    public AdminController(QuestService questService, ThemeService themeService) {
        this.questService = questService;
        this.themeService = themeService;
    }

    @PostMapping("/quest")
    public ApiMessage saveQuest(@RequestBody Quest quest){
        return questService.save(quest);
    }

    @PostMapping("/theme")
    public ApiMessage saveTheme(@RequestBody Theme theme){
        return themeService.save(theme);
    }
}
