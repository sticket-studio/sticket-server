package com.ec.sticket.services;

import com.ec.sticket.models.Advertisement;
import com.ec.sticket.repositories.AdvertisementRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository, UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    public Advertisement findById(int advertisementId) {
        Optional<Advertisement> advertisement = advertisementRepository.findById(advertisementId);
        return advertisement.orElseGet(Advertisement::new);
    }

    public ApiMessage save(Advertisement advertisement) {
        if (advertisement != null && advertisement.getStick() != 0) {
            advertisementRepository.save(advertisement);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(Advertisement modified) {
        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(modified.getId());
        if (advertisementOptional.isPresent()) {
            Advertisement advertisement = advertisementOptional.get();

            advertisement.setStick(modified.getStick());
            advertisement.setImgUrl(modified.getImgUrl());

            advertisementRepository.save(advertisement);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int advertisementId) {
        Optional<Advertisement> advertisement = advertisementRepository.findById(advertisementId);
        if (advertisement.isPresent()) {
            advertisementRepository.deleteById(advertisementId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

//    @Transactional
//    public ApiMessage watchAdvertisement(User user, int advertisementId) {
//        Optional<Advertisement> advertisement = advertisementRepository.findById(advertisementId);
//
//        if (advertisement.isPresent()) {
//            userAdvertisementPurchaseRepository.save(new UserAdvertisementPurchase(user, advertisement.get()));
//            user.setAdvertisement(user.getAdvertisement() + advertisement.get().getAdvertisement());
//            userRepository.save(user);
//            return ApiMessage.getSuccessMessage();
//        } else {
//            return ApiMessage.getFailMessage();
//        }
//    }
}
