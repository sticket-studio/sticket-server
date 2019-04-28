package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Theme;
import com.ec.sticket.services.ThemeService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/theme")
@Api(value = "ThemeController", description = "테마 컨트롤러")
public class ThemeController {
    private final UserService userService;
    private final ThemeService themeService;

    public ThemeController(UserService userService, ThemeService themeService) {
        this.userService = userService;
        this.themeService = themeService;
    }

    @GetMapping("")
    public List<Theme> findAllThemes(){
        return themeService.findAll();
    }

    @GetMapping("/{themeId}")
    public Theme findThemeById(@PathVariable("themeId") int themeId){
        return themeService.findById(themeId);
    }

    @PostMapping("")
    public ApiMessage saveTheme(@RequestBody Theme theme){
        return themeService.save(theme);
    }

    @PutMapping("")
    public ApiMessage updateTheme(@RequestBody Theme theme){
        return themeService.update(theme);
    }

    @DeleteMapping("/{themeId}")
    public ApiMessage deleteTheme(@PathVariable("themeId") int themeId){
        return themeService.delete(themeId);
    }
}
