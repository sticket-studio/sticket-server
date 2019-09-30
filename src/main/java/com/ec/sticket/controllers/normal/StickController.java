package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Stick;
import com.ec.sticket.services.StickService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import com.ec.sticket.util.JwtParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/sticks")
@Api(value = "StickController", description = "스틱 컨트롤러")
public class StickController {
    private final UserService userService;
    private final StickService stickService;
    private final JwtParser jwtParser;

    public StickController(UserService userService, StickService stickService, JwtParser jwtParser) {
        this.userService = userService;
        this.stickService = stickService;
        this.jwtParser = jwtParser;
    }

    @GetMapping
    @ApiOperation(value = "스틱 찾기", notes = "모든 스틱 찾기")
    public List<Stick> findAllSticks() {
        return stickService.findAll();
    }

    @GetMapping("/{stickId}")
    @ApiOperation(value = "스틱 찾기 : 스틱 ID", notes = "스틱 ID로 스틱 찾기")
    public Stick findStickById(@PathVariable("stickId") int stickId) {
        return stickService.findById(stickId);
    }

    @PostMapping
    @ApiOperation(value = "스틱 저장", notes = "스틱 저장")
    public ApiMessage saveStick(@RequestBody Stick stick) {
        return stickService.save(stick);
    }

    @PutMapping
    @ApiOperation(value = "스틱 수정", notes = "스틱 수정")
    public ApiMessage updateStick(@RequestBody Stick stick) {
        return stickService.update(stick);
    }

    @DeleteMapping("/{stickId}")
    @ApiOperation(value = "스틱 삭제 : 스틱 ID", notes = "스틱 ID로 스틱 삭제")
    public ApiMessage deleteStick(@PathVariable("stickId") int stickId) {
        return stickService.delete(stickId);
    }

    @PostMapping("/{stickId}/buy")
    @ApiOperation(value = "스틱 구매", notes = "스틱 구매")
    public ApiMessage buyStick(@PathVariable("stickId") int stickId, Authentication authentication) {
        return stickService.buyStick(jwtParser.getUserFromJwt(authentication), stickId);
    }
}
