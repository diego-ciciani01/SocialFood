package com.socialfood.app.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.socialfood.app.service")
@EntityScan(basePackages = "com.socialfood.app.model")

public class SocialFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialFoodApplication.class, args);
    }

}
