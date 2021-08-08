package com.example.slackbot.slack.service;

import com.example.slackbot.weather.WeatherClient;
import com.example.slackbot.weather.dto.SearchShortTermWeatherReq;
import com.example.slackbot.weather.dto.SearchShortTermWeatherRes;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public SearchShortTermWeatherRes alarmEveryMorning() {
        Date currentTime = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");

        String date = dateFormat.format(currentTime);
        // String time = timeFormat.format(currentTime);

        SearchShortTermWeatherReq searchShortTermWeatherReq = new SearchShortTermWeatherReq();
        searchShortTermWeatherReq.setNumOfRows(100);
        searchShortTermWeatherReq.setPageNo(1);
        searchShortTermWeatherReq.setBase_date(date);
        searchShortTermWeatherReq.setBase_time("0200");

        var searchWeatherRes = weatherClient.searchWeather(searchShortTermWeatherReq);

        return searchWeatherRes;
    }
}
