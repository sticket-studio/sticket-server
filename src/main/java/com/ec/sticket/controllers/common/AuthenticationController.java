package com.ec.sticket.controllers.common;

import com.ec.sticket.dto.request.user.SignupRequest;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ApiMessage signup(@RequestBody SignupRequest request) {
        return userService.save(request);
    }

    @PostMapping("/email")
    public ApiMessage findEmail(@RequestBody SignupRequest request) {
        return userService.save(request);
    }

    @PostMapping("/password")
    public ApiMessage findRandomPassword(@RequestBody SignupRequest request) {
        return userService.save(request);
    }

}