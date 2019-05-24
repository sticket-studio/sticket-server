package com.ec.sticket.controllers.normal;

import com.ec.sticket.dto.request.user.*;
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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    //TODO: 미구현
    @PutMapping("")
    public ApiMessage updateUser(@RequestBody UserUpdateRequest user){
        return null;
//        return userService.update(user);
    }

    @DeleteMapping("/{userId}")
    public ApiMessage deleteUser(@PathVariable("userId") int userId){
        return userService.delete(userId);
    }

    //TODO: 미구현
    @PostMapping("/like/user")
    @ApiOperation(value = "작가 좋아요", notes = "User 좋아요")
    @ApiImplicitParam(name = "user", value = "작가 좋아요", required = true,  paramType= "body")
    public ApiMessage deleteAsset(@RequestBody UserLikeRequest request) {
//        return userService.like(request);
        return null;
    }

    //TODO: 미구현
    @PostMapping("/like/asset")
    @ApiOperation(value = "에셋 좋아요", notes = "Asset 좋아요")
    @ApiImplicitParam(name = "asset", value = "에셋 좋아요", required = true,  paramType= "body")
    public ApiMessage likeAsset(@RequestBody AssetLikeRequest request) {
        return userService.likeAsset(request);
    }

    //TODO: 미구현
    @PostMapping("/like/sticon")
    @ApiOperation(value = "스티콘 좋아요", notes = "Sticon 좋아요")
    @ApiImplicitParam(name = "sticon", value = "스티콘 좋아요", required = true,  paramType= "body")
    public ApiMessage likeSticon(@RequestHeader(value = "Authorization", required = false) String jwtToken,
                                 @RequestBody SticonLikeRequest request) {
        return userService.likeSticon(request);
    }

    //TODO: 미구현
    @PostMapping("/like/motionticon")
    @ApiOperation(value = "모션티콘 좋아요", notes = "Motionticon 좋아요")
    @ApiImplicitParam(name = "motionticon", value = "모션티콘 좋아요", required = true,  paramType= "body")
    public ApiMessage likeMotionticon(@RequestBody MotionticonLikeRequest request) {
        return userService.likeMotionticon(request);
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