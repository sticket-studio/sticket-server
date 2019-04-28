package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.services.StickerService;
import com.ec.sticket.util.ApiMessage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/sticker")
@Api(value = "StickerController", description = "스티콘 컨트롤러")
public class StickerController {

    private final StickerService stickerService;

    public StickerController(StickerService stickerService) {
        this.stickerService = stickerService;
    }

    @GetMapping("")
    public List<Sticker> findAllStickers(){
        return stickerService.findAll();
    }

    @GetMapping("/{stickerId}")
    public Sticker findStickerById(@PathVariable("stickerId") int stickerId){
        return stickerService.findById(stickerId);
    }

    @PostMapping("{authorId}")
    public ApiMessage saveSticker(@PathVariable("authorId") int authorId, @RequestBody Sticker sticker){
        return stickerService.save(authorId, sticker);
    }

    @PutMapping("")
    public ApiMessage updateSticker(@RequestBody Sticker sticker){
        return stickerService.update(sticker);
    }

    @DeleteMapping("/{stickerId}")
    public ApiMessage deleteSticker(@PathVariable("stickerId") int stickerId){
        return stickerService.delete(stickerId);
    }

    @GetMapping("/author/{authorId}")
    public List<Sticker> getStickersByAuthorId(@PathVariable("authorId") int authorId) {
        return stickerService.getStickersByAuthorId(authorId);
    }

    @GetMapping("/buyer/{buyerId}")
    public List<Sticker> getStickersByBuyerId(@PathVariable("buyerId") int buyerId) {
        return stickerService.getStickersByBuyerId(buyerId);
    }

    @GetMapping("/asset/{assetId}")
    public List<Sticker> getStickersByAssetId(@PathVariable("assetId") int stickerId) {
        return stickerService.getStickersByAssetId(stickerId);
    }

    @GetMapping("/motionticon/{motionticonId}")
    public List<Sticker> getStickersByMotionticonId(@PathVariable("motionticonId") int motionticonId) {
        return stickerService.getStickersByMotionticonId(motionticonId);
    }

    @GetMapping("/theme/{themeId}")
    public List<Sticker> getStickersByThemeId(@PathVariable("themeId") int themeId) {
        return stickerService.getStickersByThemeId(themeId);
    }
}
