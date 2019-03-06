package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.services.StickerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/normal/sticker")
public class StickerController {

    private final StickerService stickerService;

    public StickerController(StickerService stickerService) {
        this.stickerService = stickerService;
    }

    @GetMapping("/{stickerId}")
    public Sticker getStickerById(@PathVariable("stickerId") int stickerId) {
        return stickerService.getStickerById(stickerId);
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
