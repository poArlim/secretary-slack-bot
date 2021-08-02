package com.example.slackbot.slack.service;

import com.example.slackbot.slack.SlackClient;
import com.example.slackbot.slack.dto.PostMessageReq;
import com.example.slackbot.slack.repository.PostMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostMessageService {

    private final SlackClient slackClient;
    private final PostMessageRepository postMessageRepository;

    public String send(String message){
        var postMessageReq = new PostMessageReq();
        postMessageReq.setText(message);

        var postMessageRes = slackClient.postMessage(postMessageReq);

        return postMessageRes.toString();
    }
}
