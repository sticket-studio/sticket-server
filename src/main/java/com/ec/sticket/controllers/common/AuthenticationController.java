package com.ec.sticket.controllers.common;

import com.ec.sticket.dto.request.auth.FindPasswordRequest;
import com.ec.sticket.dto.request.user.SignupRequest;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;
    private final ConsumerTokenServices tokenServices;
    private final JavaMailSender javaMailSender;

    public AuthenticationController(UserService userService, DefaultTokenServices tokenServices,
                                    JavaMailSender javaMailSender) {
        this.userService = userService;
        this.tokenServices = tokenServices;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/signup")
    public ApiMessage signup(@RequestBody SignupRequest request) {
        return userService.save(request);
    }

    @PostMapping("/email")
    public ApiMessage findEmail(@RequestBody FindPasswordRequest request) {
        return ApiMessage.getFailMessage("미구현");
    }

    @PostMapping("/password")
    public ApiMessage findRandomPassword(@RequestBody FindPasswordRequest request) {
        String newPassword = userService.findRandomPassword(request);
        if (newPassword != null) {
            sendEmail(request.getEmail(), "Sticket - 비밀번호 변경 안내", "새로운 비밀번호: " + newPassword);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage("Doesn't exist email");
        }
    }

    //TODO: Refresh Token도 삭제하려면 JDBCTokenStore 사용해야 함
    // 참고: https://www.baeldung.com/spring-security-oauth-revoke-tokens
    @DeleteMapping("/signout")
    public ApiMessage signout(Authentication authentication) {
        log.info(authentication.toString());
        OAuth2AuthenticationDetails oAuth2Authentication = (OAuth2AuthenticationDetails) authentication.getDetails();
        tokenServices.revokeToken(oAuth2Authentication.getTokenValue());
        return ApiMessage.getSuccessMessage();
    }


    void sendEmail(String to, String subject, String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(message);

        javaMailSender.send(msg);
    }
}