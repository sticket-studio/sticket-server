package com.ec.sticket.services;

import com.ec.sticket.models.Sticker;
import com.ec.sticket.models.User;
import com.ec.sticket.repositories.StickerRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StickerService {

    private final UserRepository userRepository;
    private final StickerRepository stickerRepository;

    public StickerService(UserRepository userRepository, StickerRepository stickerRepository) {
        this.userRepository = userRepository;
        this.stickerRepository = stickerRepository;
    }

    public Sticker findById(int stickerid) {
        Optional<Sticker> stickerOptional = stickerRepository.findById(stickerid);
        return stickerOptional.orElseGet(Sticker::new);
    }

    public List<Sticker> findAll() {
        return stickerRepository.findAll();
    }

    public ApiMessage save(int authorId, Sticker sticker) {
        Optional<User> author = userRepository.findById(authorId);
        if (sticker != null && author.isPresent()) {
            sticker.setAuthor(author.get());

            stickerRepository.save(sticker);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(Sticker modified) {
        Optional<Sticker> stickerOptional = stickerRepository.findById(modified.getId());
        if (stickerOptional.isPresent()) {
            Sticker sticker = stickerOptional.get();

            sticker.setName(modified.getName());
            sticker.setDescription(modified.getDescription());
            // 저자는 변경불가
//            sticker.setAuthor(modified.getAuthor());
            sticker.setImgUrl(modified.getImgUrl());
            sticker.setThemes(modified.getThemes());
            sticker.setPrice(modified.getPrice());

            stickerRepository.save(sticker);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int stickerId) {
        Optional<Sticker> sticker = stickerRepository.findById(stickerId);
        if (sticker.isPresent()) {
            stickerRepository.deleteById(stickerId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
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
