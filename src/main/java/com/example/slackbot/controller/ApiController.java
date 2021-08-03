package com.example.slackbot.controller;

import com.example.slackbot.slack.service.PostMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slack-bot")
@RequiredArgsConstructor
public class ApiController {

    private final PostMessageService postMessageService;

    @PostMapping("/send/{message}")
    public String send(@PathVariable String message){

        var res = postMessageService.send(message);
        return res;
    }
}
