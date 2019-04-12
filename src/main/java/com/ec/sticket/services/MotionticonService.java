package com.ec.sticket.services;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.models.User;
import com.ec.sticket.repositories.MotionticonRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.util.ApiMessage;

import java.util.List;
import java.util.Optional;

public class MotionticonService {
    private UserRepository userRepository;
    private MotionticonRepository motionticonRepository;

    public MotionticonService(UserRepository userRepository, MotionticonRepository motionticonRepository){
        this.userRepository = userRepository;
        this.motionticonRepository = motionticonRepository;
    }

    public Motionticon findById(int motionticonId){
        Optional<Motionticon> motionticonOptional = motionticonRepository.findById(motionticonId);
        return motionticonOptional.orElseGet(Motionticon::new);
    }

    public List<Motionticon> findAll(){
        return motionticonRepository.findAll();
    }

    public ApiMessage save(int authorId,Motionticon motionticon){
        Optional<User> author = userRepository.findById(authorId);
        if(motionticon != null && author.isPresent()){
            motionticon.setAuthor(author.get());

            motionticonRepository.save(motionticon);
            return ApiMessage.getSuccessMessage();
        }else{
            return ApiMessage.getFailMessage();
        }

    }

    public ApiMessage update(Motionticon modified){
        Optional<Motionticon> motionticonOptional = motionticonRepository.findById(modified.getId());
        if(motionticonOptional.isPresent()){
            Motionticon motion = motionticonOptional.get();

            motion.setName(modified.getName());
            motion.setDescription(modified.getDescription());
            motion.setImgUrl(modified.getImgUrl());
            motion.setThemes(modified.getThemes());
            motion.setPrice(modified.getPrice());
            motion.setMotion(modified.getMotion());

            motionticonRepository.save(motion);
            return ApiMessage.getSuccessMessage();
        }else{
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int motionticonId){
        Optional<Motionticon> motionticon = motionticonRepository.findById(motionticonId);
        if(motionticon.isPresent()){
            motionticonRepository.deleteById(motionticonId);
            return ApiMessage.getSuccessMessage();
        }else{
            return ApiMessage.getFailMessage();
        }
    }

    public List<Motionticon> getMotionticonByAuthorId(int authorId){
        return motionticonRepository.findAllByAssetId(authorId);
    }

    public List<Motionticon> getMotionticonByBuyerId(int buyerId){
        return motionticonRepository.findAllByBuyerId(buyerId);
    }

    public List<Motionticon> getMotionticonByStickerId(int stickerId){
        return motionticonRepository.findAllByStickerId(stickerId);
    }

    public List<Motionticon> getMotionticonByAssetId(int assetId){
        return motionticonRepository.findAllByAssetId(assetId);
    }
    public List<Motionticon> getMotionticonByThemeId(int themeId){
        return motionticonRepository.findAllByThemeId(themeId);
    }






}
