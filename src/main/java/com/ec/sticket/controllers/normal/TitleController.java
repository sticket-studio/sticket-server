package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Title;
import com.ec.sticket.services.TitleService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/title")
public class TitleController {
    private final UserService userService;
    private final TitleService titleService;

    public TitleController(UserService userService, TitleService titleService) {
        this.userService = userService;
        this.titleService = titleService;
    }

    @GetMapping("")
    public List<Title> findAllTitles(){
        return titleService.findAll();
    }

    @GetMapping("/{titleId}")
    public Title findTitleById(@PathVariable("titleId") int titleId){
        return titleService.findById(titleId);
    }

    @PostMapping("")
    public ApiMessage saveTitle(@RequestBody Title title){
        return titleService.save(title);
    }
}
