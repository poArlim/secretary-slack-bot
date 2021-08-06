package com.example.slackbot.weather;

import com.example.slackbot.weather.dto.SearchShortTermWeatherReq;
import com.example.slackbot.weather.dto.SearchShortTermWeatherRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class WeatherClient {

    @Value("${weather.url.search.shortTerm}")
    private String weatherShortTermSearchUrl;

    @Value("${weather.key.encoding}")
    private String weatherEncodingKey;

    @Value("${weather.key.decoding}")
    private String weatherDecodingKey;

    public String searchWeather(SearchShortTermWeatherReq searchShortTermWeatherReq) {
        searchShortTermWeatherReq.setServiceKey(weatherEncodingKey);

        var uri = UriComponentsBuilder.fromUriString(weatherShortTermSearchUrl)
                .queryParams(searchShortTermWeatherReq.toMultiValueMap())
                .build(true)
                .toUri();

        System.out.println(uri);

        var headers = new HttpHeaders();
        var httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchShortTermWeatherRes>(){};

        var responseEntity = new RestTemplate().exchange(
                uri.toString(),
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        return responseEntity.toString();
    }
}
