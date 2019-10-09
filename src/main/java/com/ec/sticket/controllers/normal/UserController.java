package com.ec.sticket.controllers.normal;

import com.ec.sticket.dto.request.user.UserUpdateRequest;
import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Quest;
import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserQuest;
import com.ec.sticket.services.QuestService;
import com.ec.sticket.services.StickService;
import com.ec.sticket.services.TitleService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.services.mapping.UserQuestService;
import com.ec.sticket.util.ApiMessage;
import com.ec.sticket.util.JwtParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/users")
@Api(value = "UserController", description = "유저 컨트롤러")
public class UserController {
    private final UserService userService;
    private final StickService stickService;
    private final QuestService questService;
    private final UserQuestService userQuestService;
    private final TitleService titleService;
    private final JwtParser jwtParser;

    public UserController(UserService userService, StickService stickService, QuestService questService,
                          UserQuestService userQuestService, TitleService titleService, JwtParser jwtParser) {
        this.userService = userService;
        this.stickService = stickService;
        this.questService = questService;
        this.userQuestService = userQuestService;
        this.titleService = titleService;
        this.jwtParser = jwtParser;
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        return userService.findById(userId);
    }

    @GetMapping("/me")
    @ApiOperation(value = "내 정보 조회 1", notes = "내 정보 조회 2")
    @ApiImplicitParam(name = "user", value = "내 정보 조회 3")
    public User findUserByToken(Authentication authentication) {
        return jwtParser.getUserFromJwt(authentication);
    }

    @PutMapping
    public ApiMessage updateUser(Authentication authentication, @RequestBody UserUpdateRequest user) {
        int userId = jwtParser.getUserIdFromJwt(authentication);
        return userService.update(userId, user);
    }

    @DeleteMapping("/{userId}")
    public ApiMessage deleteUser(@PathVariable("userId") int userId) {
        return userService.delete(userId);
    }

    @GetMapping("/like")
    @ApiOperation(value = "작가 좋아요 조회 1", notes = "작가 좋아요 조회 2")
    @ApiImplicitParam(name = "user", value = "작가 좋아요 조회 3")
    public ApiMessage findLikes(@AuthenticationPrincipal UserDetails userDetails) {
        int followerId = userService.findByEmail(userDetails.getUsername()).getId();
        return userService.findLike(followerId);
    }

    @GetMapping("/like/{followingId}")
    @ApiOperation(value = "작가 좋아요 조회 1", notes = "작가 좋아요 조회 2")
    @ApiImplicitParam(name = "user", value = "작가 좋아요 조회 3")
    public ApiMessage findLikes(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int followingId) {
        int followerId = userService.findByEmail(userDetails.getUsername()).getId();
        return userService.findLike(followerId, followingId);
    }

    @PostMapping("/like/{followingId}")
    @ApiOperation(value = "작가 좋아요", notes = "작가 좋아요")
    @ApiImplicitParam(name = "user", value = "작가 좋아요")
    public ApiMessage like(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int followingId) {
        int followerId = userService.findByEmail(userDetails.getUsername()).getId();
        return userService.likeUser(followerId, followingId);
    }

    @DeleteMapping("/dislike/{followingId}")
    @ApiOperation(value = "작가 좋아요 취소", notes = "작가 좋아요 취소")
    @ApiImplicitParam(name = "user", value = "작가 좋아요 취소")
    public ApiMessage dislike(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int followingId) {
        int followerId = userService.findByEmail(userDetails.getUsername()).getId();
        return userService.dislikeUser(followerId, followingId);
    }

    @PostMapping("/{userId}/asset")
    public ApiMessage addSellingAsset(@PathVariable("userId") int userId, @RequestBody Asset asset) {
        return userService.addSellingAsset(userId, asset);
    }

    @PostMapping("/{userId}/sticon")
    public ApiMessage addSellingSticon(@PathVariable("userId") int userId, @RequestBody Sticon sticon) {
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