package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Quest;
import com.ec.sticket.services.QuestService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/quest")
@Api(value = "QuestController", description = "퀘스트 컨트롤러")
public class QuestController {

    private final QuestService questService;
    private final UserService userService;

    public QuestController(QuestService questService, UserService userService) {
        this.questService = questService;
        this.userService = userService;
    }

    @GetMapping("")
    @ApiOperation(value = "퀘스트 찾기", notes = "모든 퀘스트 찾기")
    public List<Quest> findAllQuests(){
        return questService.findAll();
    }

    @GetMapping("/{questId}")
    @ApiOperation(value = "퀘스트 찾기 : questId", notes = "퀘스트 ID로 퀘스트 찾기")
    public Quest findQuestById(@PathVariable("questId") int questId){
        return questService.findById(questId);
    }

    @PostMapping("")
    @ApiOperation(value = "퀘스트 저장", notes = "퀘스트 저장")
    public ApiMessage saveQuest(@RequestBody Quest quest){
        return questService.save(quest);
    }

    @PutMapping("")
    @ApiOperation(value = "퀘스트 수정", notes = "퀘스트 수정")
    public ApiMessage updateQuest(@RequestBody Quest quest){
        return questService.update(quest);
    }

    @DeleteMapping("/{questId}")
    @ApiOperation(value = "퀘스트 삭제", notes = "퀘스트 삭제")
    public ApiMessage deleteQuest(@PathVariable("questId") int questId){
        return questService.delete(questId);
    }
}
