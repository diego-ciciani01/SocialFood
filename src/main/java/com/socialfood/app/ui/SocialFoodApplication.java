package com.socialfood.app.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.socialfood.app")
@EnableJpaRepositories(basePackages = {"com.socialfood.app.repository"})
@EntityScan(basePackages = "com.socialfood.app.model")
public class SocialFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialFoodApplication.class, args);
    }

}
