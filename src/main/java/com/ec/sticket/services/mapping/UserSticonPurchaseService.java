package com.ec.sticket.services.mapping;

import com.ec.sticket.models.mapping.UserSticonPurchase;
import com.ec.sticket.models.mapping.compositekey.UserSticonPurchaseKey;
import com.ec.sticket.repositories.mapping.purchase.UserSticonPurchaseRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSticonPurchaseService {
    private final UserSticonPurchaseRepository userSticonPurchaseRepository;

    public UserSticonPurchaseService(UserSticonPurchaseRepository userSticonPurchaseRepository) {
        this.userSticonPurchaseRepository = userSticonPurchaseRepository;
    }

    public List<UserSticonPurchase> findAll() {
        return userSticonPurchaseRepository.findAll();
    }

    public UserSticonPurchase findById(UserSticonPurchaseKey userSticonPurchaseKey){
        Optional<UserSticonPurchase> userSticonPurchaseOptional
                = userSticonPurchaseRepository.findById(userSticonPurchaseKey);
        return userSticonPurchaseOptional.orElseGet(UserSticonPurchase::new);
    }

    public ApiMessage save(UserSticonPurchase userSticonPurchase) {
        if (userSticonPurchase != null && userSticonPurchase.getSticon() != null
                && userSticonPurchase.getUser() != null) {
            userSticonPurchaseRepository.save(userSticonPurchase);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

//    public ApiMessage update(UserSticonPurchase modified) {
//        Optional<UserSticonPurchase> userSticonPurchaseOptional = userSticonPurchaseRepository.findById(modified.getId());
//        if (userSticonPurchaseOptional.isPresent()) {
//            UserSticonPurchase userSticonPurchase = userSticonPurchaseOptional.get();
//
//            userSticonPurchaseRepository.save(userSticonPurchase);
//            return ApiMessage.getSuccessMessage();
//        } else {
//            return ApiMessage.getFailMessage();
//        }
//    }

    public ApiMessage delete(UserSticonPurchaseKey userSticonPurchaseId) {
        Optional<UserSticonPurchase> userSticonPurchase = userSticonPurchaseRepository.findById(userSticonPurchaseId);
        if (userSticonPurchase.isPresent()) {
            userSticonPurchaseRepository.deleteById(userSticonPurchaseId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
    
}
