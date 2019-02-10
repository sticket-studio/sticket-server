package com.ec.sticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SticketApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SticketApplication.class, args);
    }

}

