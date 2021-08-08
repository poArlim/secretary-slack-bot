package com.example.slackbot.weather;

import com.example.slackbot.weather.dto.SearchShortTermWeatherReq;
import com.example.slackbot.weather.dto.SearchShortTermWeatherRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherClient {

    @Value("${weather.url.search.shortTerm}")
    private String weatherShortTermSearchUrl;

    @Value("${weather.key.encoding}")
    private String weatherEncodingKey;

    @Value("${weather.key.decoding}")
    private String weatherDecodingKey;

    public SearchShortTermWeatherRes searchWeather(SearchShortTermWeatherReq searchShortTermWeatherReq) {
        searchShortTermWeatherReq.setServiceKey(weatherEncodingKey);

        var uri = UriComponentsBuilder.fromUriString(weatherShortTermSearchUrl)
                .queryParams(searchShortTermWeatherReq.toMultiValueMap())
                .build(true)
                .toUri();

        var headers = new HttpHeaders();
        var httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchShortTermWeatherRes>(){};

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }
}
