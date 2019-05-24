package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.mapping.UserSticonPurchase;
import com.ec.sticket.services.SticonService;
import com.ec.sticket.util.ApiMessage;
import com.ec.sticket.util.JwtParser;
import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/normal/sticons")
@Api(value = "SticonController", description = "스티콘 컨트롤러")
public class SticonController {

    private final SticonService sticonService;
    private final JwtParser jwtParser;

    public SticonController(SticonService sticonService, JwtParser jwtParser) {
        this.sticonService = sticonService;
        this.jwtParser = jwtParser;
    }

    @GetMapping("")
    public List<Sticon> findAllSticon(){
        return sticonService.findAll();
    }

    @GetMapping("/{sticonId}")
    public Sticon findSticonById(@PathVariable("sticonId") int sticonId){
        return sticonService.findById(sticonId);
    }

    @PostMapping("{authorId}")
    public ApiMessage saveSticon(@PathVariable("authorId") int authorId, @RequestBody Sticon sticon){
        return sticonService.save(authorId, sticon);
    }

    @PutMapping("")
    public ApiMessage updateSticon(@RequestBody Sticon sticon){
        return sticonService.update(sticon);
    }

    @DeleteMapping("/{sticonId}")
    public ApiMessage deleteSticon(@PathVariable("sticonId") int sticonId){
        return sticonService.delete(sticonId);
    }

    @GetMapping("/author/{authorId}")
    public List<Sticon> getSticonsByAuthorId(@PathVariable("authorId") int authorId) {
        return sticonService.getSticonsByAuthorId(authorId);
    }

    @GetMapping("/buyer")
    public List<Sticon> getSticonsByBuyerId(Authentication authentication) {
        return jwtParser.getUserFromJwt(authentication).getUserSticonPurchases()
                .stream().map(UserSticonPurchase::getSticon).collect(Collectors.toList());
    }

    @GetMapping("/asset/{assetId}")
    public List<Sticon> getSticonsByAssetId(@PathVariable("assetId") int assetId) {
        return sticonService.getSticonsByAssetId(assetId);
    }

    @GetMapping("/motionticon/{motionticonId}")
    public List<Sticon> getSticonsByMotionticonId(@PathVariable("motionticonId") int motionticonId) {
        return sticonService.getSticonsByMotionticonId(motionticonId);
    }

    @GetMapping("/theme/{themeId}")
    public List<Sticon> getSticonsByThemeId(@PathVariable("themeId") int themeId) {
        return sticonService.getSticonsByThemeId(themeId);
    }
}
