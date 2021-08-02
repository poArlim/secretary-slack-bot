package com.example.slackbot.slack.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostMessageReq {

    private String channel = "";

    private String text = "";
}
