package com.rewards.openrewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OpenRewardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenRewardsApplication.class, args);
    }

}
