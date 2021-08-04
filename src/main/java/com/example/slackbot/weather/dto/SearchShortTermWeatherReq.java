package com.example.slackbot.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchShortTermWeatherReq {

    private String serviceKey;
    private int numOfRows;
    private int pageNo;
    private String dataType = "JSON";
    private String base_date;
    private String base_time;
    private int nx = 61;
    private int ny = 130;

    public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("serviceKey", serviceKey);
        map.add("numOfRows", String.valueOf(numOfRows));
        map.add("pageNo", String.valueOf(pageNo));
        map.add("dataType", dataType);
        map.add("base_date", base_date);
        map.add("base_time", base_time);
        map.add("nx", String.valueOf(nx));
        map.add("ny", String.valueOf(ny));
        return map;
    }
}
