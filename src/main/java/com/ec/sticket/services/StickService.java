package com.ec.sticket.services;

import com.ec.sticket.models.Stick;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserStickPurchase;
import com.ec.sticket.repositories.StickRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.repositories.mapping.purchase.UserStickPurchaseRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StickService {
    private final StickRepository stickRepository;
    private final UserRepository userRepository;
    private final UserStickPurchaseRepository userStickPurchaseRepository;

    public StickService(StickRepository stickRepository, UserRepository userRepository, UserStickPurchaseRepository userStickPurchaseRepository) {
        this.stickRepository = stickRepository;
        this.userRepository = userRepository;
        this.userStickPurchaseRepository = userStickPurchaseRepository;
    }

    public List<Stick> findAll() {
        return stickRepository.findAll();
    }

    public Stick findById(int stickId) {
        Optional<Stick> stick = stickRepository.findById(stickId);
        return stick.orElseGet(Stick::new);
    }

    public ApiMessage save(Stick stick) {
        if (stick != null && stick.getCash() != 0 && stick.getStick() != 0) {
            stickRepository.save(stick);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(Stick modified) {
        Optional<Stick> stickOptional = stickRepository.findById(modified.getId());
        if (stickOptional.isPresent()) {
            Stick stick = stickOptional.get();

            stick.setCash(modified.getCash());
            stick.setStick(modified.getStick());
            stick.setImgUrl(modified.getImgUrl());

            stickRepository.save(stick);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int stickId) {
        Optional<Stick> stick = stickRepository.findById(stickId);
        if (stick.isPresent()) {
            stickRepository.deleteById(stickId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    @Transactional
    public ApiMessage buyStick(User user, int stickId) {
        Optional<Stick> stick = stickRepository.findById(stickId);

        if (stick.isPresent()) {
            userStickPurchaseRepository.save(new UserStickPurchase(user, stick.get()));
            user.setStick(user.getStick() + stick.get().getStick());
            userRepository.save(user);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
}
