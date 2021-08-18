package com.example.slackbot.controller;

import com.example.slackbot.slack.service.PostMessageService;
import com.example.slackbot.slack.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slack-bot")
@RequiredArgsConstructor
public class ApiController {

    private final PostMessageService postMessageService;
    private final WeatherService weatherService;

    @PostMapping("/send/{message}")
    public String send(@PathVariable String message){

        var res = postMessageService.send(message);
        return res;
    }

    @GetMapping("/alarm")
    @Scheduled(cron = "0 0 6 * * *")
    public String alarm() {
        return postMessageService.send(weatherService.alarmEveryMorning());
    }
}
