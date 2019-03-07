package com.ec.sticket.services;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.repositories.AssetRepository;
import com.ec.sticket.repositories.StickerRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final StickerRepository stickerRepository;
//    private final MotionticonRepository motionticonRepository;

    public UserService(UserRepository userRepository, AssetRepository assetRepository
            , StickerRepository stickerRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.stickerRepository = stickerRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseGet(User::new);
    }

    public ApiMessage save(User user) {
        if (user != null) {
            userRepository.save(user);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(User modified) {
        Optional<User> userOptional = userRepository.findById(modified.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.update(modified);

            userRepository.save(user);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage addSellingAsset(int userId, Asset asset) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            asset.setAuthor(user);

            assetRepository.save(asset);

            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
}
