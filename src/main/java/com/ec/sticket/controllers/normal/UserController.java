package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.CashItem;
import com.ec.sticket.models.Quest;
import com.ec.sticket.models.Theme;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserQuest;
import com.ec.sticket.services.CashItemService;
import com.ec.sticket.services.QuestService;
import com.ec.sticket.services.ThemeService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.services.mapping.UserQuestService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/user")
public class UserController {
    private final UserService userService;
    private final CashItemService cashItemService;
    private final QuestService questService;
    private final UserQuestService userQuestService;
    private final ThemeService themeService;

    public UserController(UserService userService, CashItemService cashItemService, QuestService questService, UserQuestService userQuestService, ThemeService themeService) {
        this.userService = userService;
        this.cashItemService = cashItemService;
        this.questService = questService;
        this.userQuestService = userQuestService;
        this.themeService = themeService;
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/")
    public ApiMessage saveUser(@RequestBody User user) {
        if (user != null) {
            userService.saveUser(user);
            return new ApiMessage(ApiMessage.Status.SUCCESS);
        } else {
            return new ApiMessage(ApiMessage.Status.FAIL);
        }
    }

    @GetMapping("/cashitem")
    public List<CashItem> findAllCashItems() {
        return cashItemService.findAll();
    }

    @GetMapping("/quest")
    public List<Quest> findAllQuests() {
        return questService.findAll();
    }

    @GetMapping("/quest/{questId}")
    public Quest findQuest(@PathVariable("questId") int questId) {
        return questService.findById(questId);
    }

    // 이건 나중에 userId 없애도 될 듯
    // 헤더에 토큰값을 넣을 것이기 때문
    @GetMapping("/userquest/user/{userId}")
    public List<UserQuest> findQuestByUserId(@PathVariable("userId") int userId) {
        return userService.findUserById(userId).getUserQuests();
    }

    @PostMapping("/quest/{questId}/user/{userId}")
    public ApiMessage saveUserQuest(@PathVariable("userId") int userId, @PathVariable("questId") int questId) {
        User user = userService.findUserById(userId);
        Quest quest = questService.findById(questId);

        return userQuestService.save(user,quest);
    }

    @GetMapping("/theme")
    public List<Theme> findAllThemes() {
        return themeService.findAll();
    }
}