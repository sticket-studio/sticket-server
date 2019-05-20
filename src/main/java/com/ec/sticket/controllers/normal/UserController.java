package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Quest;
import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserQuest;
import com.ec.sticket.services.CashItemService;
import com.ec.sticket.services.QuestService;
import com.ec.sticket.services.TitleService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.services.mapping.UserQuestService;
import com.ec.sticket.util.ApiMessage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/users")
@Api(value = "UserController", description = "유저 컨트롤러")
public class UserController {
    private final UserService userService;
    private final CashItemService cashItemService;
    private final QuestService questService;
    private final UserQuestService userQuestService;
    private final TitleService titleService;

    public UserController(UserService userService, CashItemService cashItemService, QuestService questService, UserQuestService userQuestService, TitleService titleService) {
        this.userService = userService;
        this.cashItemService = cashItemService;
        this.questService = questService;
        this.userQuestService = userQuestService;
        this.titleService = titleService;
    }

    @GetMapping("")
    public List<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable("userId") int userId){
        return userService.findById(userId);
    }

    @PostMapping("")
    public ApiMessage saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("")
    public ApiMessage updateUser(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/{userId}")
    public ApiMessage deleteUser(@PathVariable("userId") int userId){
        return userService.delete(userId);
    }

    @PostMapping("/{userId}/asset")
    public ApiMessage addSellingAsset(@PathVariable("userId") int userId, @RequestBody Asset asset){
        return userService.addSellingAsset(userId,asset);
    }

    @PostMapping("/{userId}/sticon")
    public ApiMessage addSellingSticon(@PathVariable("userId") int userId, @RequestBody Sticon sticon){
        return userService.addSellingSticon(userId, sticon);
    }

    // 이건 나중에 userId 없애도 될 듯
    // 헤더에 토큰값을 넣을 것이기 때문
    @GetMapping("/userquest/user/{userId}")
    public List<UserQuest> findQuestByUserId(@PathVariable("userId") int userId) {
        return userService.findById(userId).getUserQuests();
    }

    @PostMapping("/quest/{questId}/user/{userId}")
    public ApiMessage saveUserQuest(@PathVariable("userId") int userId, @PathVariable("questId") int questId) {
        User user = userService.findById(userId);
        Quest quest = questService.findById(questId);

        return userQuestService.save(user, quest);
    }
}