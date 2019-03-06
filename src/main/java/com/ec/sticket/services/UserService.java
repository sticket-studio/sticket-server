package com.ec.sticket.services;

import com.ec.sticket.models.User;
import com.ec.sticket.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(int userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
