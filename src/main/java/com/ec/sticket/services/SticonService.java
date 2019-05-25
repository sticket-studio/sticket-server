package com.ec.sticket.services;

import com.ec.sticket.dto.request.user.SticonLikeRequest;
import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.User;
import com.ec.sticket.repositories.SticonRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SticonService {

    private final UserRepository userRepository;
    private final SticonRepository sticonRepository;

    public SticonService(UserRepository userRepository, SticonRepository sticonRepository) {
        this.userRepository = userRepository;
        this.sticonRepository = sticonRepository;
    }

    public Sticon findById(int sticonId) {
        Optional<Sticon> sticonOptional = sticonRepository.findById(sticonId);
        return sticonOptional.orElseGet(Sticon::new);
    }

    public List<Sticon> findAll() {
        return sticonRepository.findAll();
    }

    public ApiMessage save(int authorId, Sticon sticon) {
        Optional<User> author = userRepository.findById(authorId);
        if (sticon != null && author.isPresent()) {
            sticon.setAuthor(author.get());

            sticonRepository.save(sticon);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(Sticon modified) {
        Optional<Sticon> sticonOptional = sticonRepository.findById(modified.getId());
        if (sticonOptional.isPresent()) {
            Sticon sticon = sticonOptional.get();

            sticon.setName(modified.getName());
            sticon.setDescription(modified.getDescription());
            // 저자는 변경불가
//            sticon.setAuthor(modified.getAuthor());
            sticon.setImgUrl(modified.getImgUrl());
            sticon.setThemes(modified.getThemes());
            sticon.setPrice(modified.getPrice());

            sticonRepository.save(sticon);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int sticonId) {
        Optional<Sticon> sticonOptional = sticonRepository.findById(sticonId);
        if (sticonOptional.isPresent()) {
            sticonRepository.deleteById(sticonId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    @Transactional
    public ApiMessage like(SticonLikeRequest request) {
        Optional<Sticon> sticon = sticonRepository.findById(request.getSticonId());
        if (sticon.isPresent()) {
            sticonRepository.like(request.getUserId(), request.getSticonId());
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public List<Sticon> getSticonsByAuthorId(int authorId) {
        return sticonRepository.findAllByAuthorId(authorId);
    }

    public List<Sticon> getSticonsByBuyerId(int buyerId) {
        return sticonRepository.findAllByBuyerId(buyerId);
    }

    public List<Sticon> getSticonsByAssetId(int assetId) {
        return sticonRepository.findAllByAssetId(assetId);
    }

    public List<Sticon> getSticonsByMotionticonId(int motionticonId) {
        return sticonRepository.findAllByMotionticonId(motionticonId);
    }

    public List<Sticon> getSticonsByThemeId(int themeId) {
        return sticonRepository.findAllByThemeId(themeId);
    }
}
