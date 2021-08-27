package com.example.slackbot.controller;

import com.example.slackbot.corona.CoronaClient;
import com.example.slackbot.slack.service.CoronaStateService;
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
    private final CoronaStateService coronaStateService;

    @GetMapping("/weather-alarm")
    @Scheduled(cron = "0 0 6 * * *")
    public String weatherAlarm() {
        return postMessageService.send(weatherService.alarmEveryMorning());
    }

    @GetMapping("/covid-alarm")
    @Scheduled(cron = "0 0 11 * * *")
    public String coronaAlarm() {
        return postMessageService.send(coronaStateService.alarmEveryday());
    }
}
