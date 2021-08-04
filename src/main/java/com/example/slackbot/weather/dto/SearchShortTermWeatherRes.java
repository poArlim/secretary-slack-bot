package com.example.slackbot.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchShortTermWeatherRes {

    private Header header;
    private Body body;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Body {
        private String dataType;
        private List<SearchShortTermWeatherItem> items;
        private int pageNo;
        private int numOfRows;
        private int totalCount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SearchShortTermWeatherItem {
        private String baseDate;
        private String baseTime;
        private String category;
        private String fcstDate;
        private String fcstTime;
        private float fcstValue;
        private int nx;
        private int ny;
    }
}
