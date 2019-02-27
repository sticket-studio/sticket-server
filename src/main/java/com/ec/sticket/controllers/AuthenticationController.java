package com.ec.sticket.controllers;

import com.ec.sticket.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping("/sign/in")
    public User signIn(){
        return new User();
    }


}
