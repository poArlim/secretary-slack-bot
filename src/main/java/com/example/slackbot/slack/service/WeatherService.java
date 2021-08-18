package com.example.slackbot.slack.service;

import com.example.slackbot.weather.WeatherClient;
import com.example.slackbot.weather.dto.SearchShortTermWeatherReq;
import com.example.slackbot.weather.dto.SearchShortTermWeatherRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public String alarmEveryMorning() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
        Date currentTime = new Date();

        String date = dateFormat.format(currentTime);
        String time = timeFormat.format(currentTime);

        SearchShortTermWeatherReq searchShortTermWeatherReq = new SearchShortTermWeatherReq();
        searchShortTermWeatherReq.setNumOfRows(178);    // 시간당 11개 * 16시간 + 일 최고, 최저기온 하나씩
        searchShortTermWeatherReq.setPageNo(1);         // 오늘 데이터
        searchShortTermWeatherReq.setBase_date(date);
        searchShortTermWeatherReq.setBase_time("0500"); // 5시에 발표. 6시 날씨부터 받아옴. 6~21시 -> 16시간.

        var searchWeatherRes = weatherClient.searchWeather(searchShortTermWeatherReq);
        var weatherItems =  searchWeatherRes.getResponse().getBody().getItems().getItem();

        StringBuilder fcst = new StringBuilder();

        SimpleDateFormat datePrintFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        fcst.append(datePrintFormat.format(currentTime) + " 05:00 am 기준, 오늘의 날씨입니다.\n");
        fcst.append("오전 " + getTimeWeather(weatherItems, "0600"));
        fcst.append("오전 " + getTimeWeather(weatherItems, "0900"));
        fcst.append("오후 " + getTimeWeather(weatherItems, "1200"));
        fcst.append("오후 " + getTimeWeather(weatherItems, "1500"));
        fcst.append("오후 " + getTimeWeather(weatherItems, "1800"));
        fcst.append("오후 " + getTimeWeather(weatherItems, "2100"));

        return fcst.toString();
    }

    public String getTimeWeather(List<SearchShortTermWeatherRes.SearchShortTermWeatherItem> items, String time){
        // SKY : 하늘상태
        // POP : 강수확률
        // TMP : 기온
        // WSD : 풍속
        var timeItems = items.stream().filter(w->w.getFcstTime().equals(time)).collect(Collectors.toList());
        StringBuilder timeWeather = new StringBuilder();
        timeWeather.append(String.format("%s:%s\t", time.substring(0,2),time.substring(2)));
        timeWeather.append("하늘상태 : " + skyCodeToString(Integer.parseInt(timeItems.stream().filter(w->w.getCategory().equals("SKY")).findFirst().get().getFcstValue())) + ",\t");
        timeWeather.append("강수확률 : " + timeItems.stream().filter(w->w.getCategory().equals("POP")).findFirst().get().getFcstValue() + "%,\t");
        timeWeather.append("기온 : " + timeItems.stream().filter(w->w.getCategory().equals("TMP")).findFirst().get().getFcstValue() + "℃,\t");
        timeWeather.append("풍속 : " + timeItems.stream().filter(w->w.getCategory().equals("WSD")).findFirst().get().getFcstValue() + "m/s,\n");

        return timeWeather.toString();
    }

    public String skyCodeToString(int skyCode){
        if(skyCode == 1) return "맑음";
        else if(skyCode == 3) return "구름많음";
        else if(skyCode == 4) return "흐림";
        else return "skyCode 오류";
    }
}
