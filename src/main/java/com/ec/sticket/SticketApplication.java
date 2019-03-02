package com.ec.sticket;

import com.ec.sticket.repositories.AssetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SticketApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.setProperty("Asia/Seoul", "UTC");
        SpringApplication.run(SticketApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(AssetRepository repository) {
        return args -> {
//            repository.save(new Asset(1, ));
        };
    }
}

