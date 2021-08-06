package com.example.slackbot.controller;

import com.example.slackbot.slack.service.PostMessageService;
import com.example.slackbot.slack.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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
    public String alarm() {
        var res = weatherService.alarmEveryMorning();
        return res.toString();
    }
}
