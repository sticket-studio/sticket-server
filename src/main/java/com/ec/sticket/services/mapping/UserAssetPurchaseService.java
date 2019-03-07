package com.ec.sticket.services.mapping;

import com.ec.sticket.models.mapping.UserAssetPurchase;
import com.ec.sticket.models.mapping.compositekey.UserAssetPurchaseKey;
import com.ec.sticket.repositories.mapping.UserAssetPurchaseRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAssetPurchaseService {
    private final UserAssetPurchaseRepository userAssetPurchaseRepository;

    public UserAssetPurchaseService(UserAssetPurchaseRepository userAssetPurchaseRepository) {
        this.userAssetPurchaseRepository = userAssetPurchaseRepository;
    }

    public List<UserAssetPurchase> findAll() {
        return userAssetPurchaseRepository.findAll();
    }

    public UserAssetPurchase findById(UserAssetPurchaseKey userAssetPurchaseKey){
        Optional<UserAssetPurchase> userAssetPurchaseOptional
                = userAssetPurchaseRepository.findById(userAssetPurchaseKey);
        return userAssetPurchaseOptional.orElseGet(UserAssetPurchase::new);
    }

    public ApiMessage save(UserAssetPurchase userAssetPurchase) {
        if (userAssetPurchase != null && userAssetPurchase.getAsset() != null
                && userAssetPurchase.getUser() != null) {
            userAssetPurchaseRepository.save(userAssetPurchase);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

//    public ApiMessage update(UserAssetPurchase modified) {
//        Optional<UserAssetPurchase> userAssetPurchaseOptional = userAssetPurchaseRepository.findById(modified.getId());
//        if (userAssetPurchaseOptional.isPresent()) {
//            UserAssetPurchase userAssetPurchase = userAssetPurchaseOptional.get();
//
//            userAssetPurchaseRepository.save(userAssetPurchase);
//            return ApiMessage.getSuccessMessage();
//        } else {
//            return ApiMessage.getFailMessage();
//        }
//    }

    public ApiMessage delete(UserAssetPurchaseKey userAssetPurchaseId) {
        Optional<UserAssetPurchase> userAssetPurchase = userAssetPurchaseRepository.findById(userAssetPurchaseId);
        if (userAssetPurchase.isPresent()) {
            userAssetPurchaseRepository.deleteById(userAssetPurchaseId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
    
}
