package com.ec.sticket.controllers.normal;
import com.ec.sticket.models.Motionticon;
import com.ec.sticket.services.MotionticonService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/motionticon")
public class MotionticionController {

    private final MotionticonService motionticonService;

    public MotionticionController(MotionticonService motionticonService) {
        this.motionticonService = motionticonService;
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
    public ApiMessage saveSticker(@PathVariable("authorId") int authorId, @RequestBody Motionticon motionticon){
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

    @GetMapping("/buyer/{buyerId}")
    public List<Motionticon> getMotionticonsByBuyerId(@PathVariable("buyerId") int buyerId){
        return motionticonService.getMotionticonByBuyerId(buyerId);
    }

    /*

    @GetMapping("/asset/{assetId}")
    public List<Motionticon> getMotionticonByAssetId(@PathVariable("assetId") int assetId){
        return motionticonService.getMotionticonByAssetId(assetId);
    }


    @GetMapping("/sticker/{stickerId}")
    public List<Motionticon> getMotionticonByStickerId(@PathVariable("stickerId") int stickerId){
        return motionticonService.getMotionticonByStickerId(stickerId);
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
