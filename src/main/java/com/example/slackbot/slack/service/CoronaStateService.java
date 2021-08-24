package com.example.slackbot.slack.service;

import com.example.slackbot.corona.CoronaClient;
import com.example.slackbot.corona.dto.GetCoronaInfStateReq;
import com.example.slackbot.weather.dto.SearchShortTermWeatherReq;
import com.example.slackbot.weather.dto.SearchShortTermWeatherRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoronaStateService {
    private final CoronaClient coronaClient;

    public String alarmEveryday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
        Date currentTime = new Date();

        Calendar cal = Calendar.getInstance();
        cal.add(cal.DATE, -1);
        String yesterday = dateFormat.format(cal.getTime());
        String today = dateFormat.format(currentTime);

        GetCoronaInfStateReq getCoronaInfStateReq = new GetCoronaInfStateReq();
        getCoronaInfStateReq.setStartCreateDt(yesterday);
        getCoronaInfStateReq.setEndCreateDt(today);

        var response = coronaClient.getCoronaInfState(getCoronaInfStateReq);
        var coronaItems = response.getBody().getItems();
        //var coronaItems = response.getResponse().getBody().getItems();

        var todayCnt = coronaItems.stream().filter(c->c.getStateDt().equals(today)).findFirst().get().getDecideCnt();
        var yesterdayCnt = coronaItems.stream().filter(c->c.getStateDt().equals(yesterday)).findFirst().get().getDecideCnt();

        StringBuilder stateMsg = new StringBuilder();
        SimpleDateFormat datePrintFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        stateMsg.append(datePrintFormat.format(currentTime) + " 09:00 am 기준, 어제의 코로나 확진자는 " + (todayCnt - yesterdayCnt) + "명 입니다.\n");

        return stateMsg.toString();
    }
}
