package com.example.slackbot.slack;

import com.example.slackbot.slack.dto.PostMessageReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SlackClient {

    @Value("${slack.url.postMessage}")
    private String slackPostMessageUrl;

    @Value("${slack.client.token}")
    private String slackClientToken;

    @Value("${slack.client.channel}")
    private String slackClientChannel;

    public String postMessage(PostMessageReq postMessageReq){
        var uri = UriComponentsBuilder.fromUriString(slackPostMessageUrl)
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+slackClientToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        postMessageReq.setChannel(slackClientChannel);

        var httpEntity = new HttpEntity<>(postMessageReq, headers);

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        return responseEntity.getBody();
    }

}
