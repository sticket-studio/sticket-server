package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.models.mapping.UserMotionticonPurchase;
import com.ec.sticket.services.MotionticonService;
import com.ec.sticket.util.ApiMessage;
import com.ec.sticket.util.JwtParser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/normal/motionticon")
public class MotionticionController {

    private final MotionticonService motionticonService;
    private final JwtParser jwtParser;

    public MotionticionController(MotionticonService motionticonService, JwtParser jwtParser) {
        this.motionticonService = motionticonService;
        this.jwtParser = jwtParser;
    }

    @GetMapping("")
    public List<Motionticon> findAllMotionticions(){
        return motionticonService.findAll();
    }

    @GetMapping("/{motionticonId}")
    public Motionticon findMotionticonById(@PathVariable("motionticonId") int motionticonId){
        return motionticonService.findById(motionticonId);
    }

    @PostMapping("/{authorId}")
    public ApiMessage saveSticon(@PathVariable("authorId") int authorId, @RequestBody Motionticon motionticon){
        return motionticonService.save(authorId,motionticon);
    }

    @PutMapping("")
    public ApiMessage updtaeMotionticon(@RequestBody Motionticon motionticon){
        return motionticonService.update(motionticon);
    }

    @DeleteMapping("")
    public ApiMessage updateMotionticon(@RequestBody Motionticon motionticon){
        return motionticonService.update(motionticon);
    }

    @GetMapping("/author/{authorId}")
    public List<Motionticon> getMotionticonsByAuthorId(@PathVariable("authorId") int authorId){
        return motionticonService.getMotionticonByAuthorId(authorId);
    }

    @GetMapping("/buyer")
    public List<Motionticon> getMotionticonsByBuyerId(Authentication authentication){
        return jwtParser.getUserFromJwt(authentication).getUserMotionticonPurchases()
                .stream().map(UserMotionticonPurchase::getMotionticon).collect(Collectors.toList());
    }

    /*

    @GetMapping("/asset/{assetId}")
    public List<Motionticon> getMotionticonByAssetId(@PathVariable("assetId") int assetId){
        return motionticonService.getMotionticonByAssetId(assetId);
    }


    @GetMapping("/sticon/{sticonId}")
    public List<Motionticon> getMotionticonBySticonId(@PathVariable("sticonId") int sticonId){
        return motionticonService.getMotionticonBySticonId(sticonId);
    }
    */

    @GetMapping("/motionticon/{motionticonId}")
    public List<Motionticon> getMotionticonByMotionticonId(@PathVariable("motionticonId") int motionticonId){
        return motionticonService.getMotionticionByMotionticonId(motionticonId);
    }

    @GetMapping("/theme{themeId}")
    public List<Motionticon> getMotionticonByThemeId(@PathVariable("themeId") int themeId){
        return motionticonService.getMotionticonByThemeId(themeId);
    }









}
