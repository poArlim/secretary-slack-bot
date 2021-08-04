package com.example.slackbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SlackBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlackBotApplication.class, args);
    }

}
