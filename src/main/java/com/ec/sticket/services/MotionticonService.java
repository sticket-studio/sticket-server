package com.ec.sticket.services;

import com.ec.sticket.models.Motionticon;
import com.ec.sticket.repositories.MotionticonRepository;
import com.ec.sticket.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class MotionticonService {
    private UserRepository userRepository;
    private MotionticonRepository motionticonRepository;

    public MotionticonService(UserRepository userRepository, MotionticonRepository motionticonRepository){
        this.userRepository = userRepository;
        this.motionticonRepository = motionticonRepository;
    }

    /*JPA 공부하기
    public Motionticon findById(int motionticonId){
        Optional<Motionticon> motionticonOptional = motionticonRepository
    }

    List<Motionticon> findAll(){motionticonRepository.findAll()};
    */

}
