package com.ec.sticket.services.mapping;

import com.ec.sticket.models.mapping.UserStickerPurchase;
import com.ec.sticket.models.mapping.compositekey.UserStickerPurchaseKey;
import com.ec.sticket.repositories.mapping.UserStickerPurchaseRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStickerPurchaseService {
    private final UserStickerPurchaseRepository userStickerPurchaseRepository;

    public UserStickerPurchaseService(UserStickerPurchaseRepository userStickerPurchaseRepository) {
        this.userStickerPurchaseRepository = userStickerPurchaseRepository;
    }

    public List<UserStickerPurchase> findAll() {
        return userStickerPurchaseRepository.findAll();
    }

    public UserStickerPurchase findById(UserStickerPurchaseKey userStickerPurchaseKey){
        Optional<UserStickerPurchase> userStickerPurchaseOptional
                = userStickerPurchaseRepository.findById(userStickerPurchaseKey);
        return userStickerPurchaseOptional.orElseGet(UserStickerPurchase::new);
    }

    public ApiMessage save(UserStickerPurchase userStickerPurchase) {
        if (userStickerPurchase != null && userStickerPurchase.getSticker() != null
                && userStickerPurchase.getUser() != null) {
            userStickerPurchaseRepository.save(userStickerPurchase);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

//    public ApiMessage update(UserStickerPurchase modified) {
//        Optional<UserStickerPurchase> userStickerPurchaseOptional = userStickerPurchaseRepository.findById(modified.getId());
//        if (userStickerPurchaseOptional.isPresent()) {
//            UserStickerPurchase userStickerPurchase = userStickerPurchaseOptional.get();
//
//            userStickerPurchaseRepository.save(userStickerPurchase);
//            return ApiMessage.getSuccessMessage();
//        } else {
//            return ApiMessage.getFailMessage();
//        }
//    }

    public ApiMessage delete(UserStickerPurchaseKey userStickerPurchaseId) {
        Optional<UserStickerPurchase> userStickerPurchase = userStickerPurchaseRepository.findById(userStickerPurchaseId);
        if (userStickerPurchase.isPresent()) {
            userStickerPurchaseRepository.deleteById(userStickerPurchaseId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
    
}
