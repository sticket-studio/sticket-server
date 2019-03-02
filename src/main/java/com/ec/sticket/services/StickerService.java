package com.ec.sticket.services;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.repositories.StickerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StickerService {

    private final StickerRepository stickerRepository;

    public StickerService(StickerRepository stickerRepository) {
        this.stickerRepository = stickerRepository;
    }

    // Methods
    public void addSticker(Sticker sticker) {
        stickerRepository.save(sticker);
    }

    public Sticker getStickerById(int stickerid) {
        Optional<Sticker> assetOptional = stickerRepository.findById(stickerid);
        return assetOptional.orElseGet(Sticker::new);
    }

    public List<Sticker> getStickersByAuthorId(int authorId) {
        return stickerRepository.findAllByAuthorId(authorId);
    }

    public List<Sticker> getStickersByBuyerId(int buyerId) {
        return stickerRepository.findAllByBuyerId(buyerId);
    }

    public List<Sticker> getStickersByAssetId(int stickerId) {
        return stickerRepository.findAllByAssetId(stickerId);
    }

    public List<Sticker> getStickersByMotionticonId(int landmarkId) {
        return stickerRepository.findAllByMotionticonId(landmarkId);
    }

    public List<Sticker> getStickersByThemeId(int themeId) {
        return stickerRepository.findAllByThemeId(themeId);
    }
}
