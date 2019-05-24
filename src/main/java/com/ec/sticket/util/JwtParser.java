package com.ec.sticket.util;

import com.ec.sticket.models.User;
import com.ec.sticket.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

@Slf4j
@Component
public class JwtParser {
    @Value("${jwt.jk-path}")
    private String JK_PATH;
    @Value("${jwt.jk-password}")
    private String JK_PASSWORD;
    @Value("${jwt.key-alias}")
    private String KEY_ALIAS;
    @Value("${jwt.key-password}")
    private String KEY_PASSWORD;

    private PublicKey publicKey;

    private final UserService userService;

    public JwtParser(UserService userService) {
        this.userService = userService;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(new FileInputStream("trustServer.cer"));
            publicKey = cert.getPublicKey();
        } catch (CertificateException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserNameFromJwt(String token) {
        Jws<Claims> parsedToken = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token.replace("Bearer ", ""));

        return parsedToken.getBody().get("user_name").toString();
    }

    // TODO: JWT에 email이 아닌 userId를 넣는 방법을 구해보자
    public User getUserFromJwt(Authentication authentication){
        return userService.findByEmail(authentication.getName());
    }
}
