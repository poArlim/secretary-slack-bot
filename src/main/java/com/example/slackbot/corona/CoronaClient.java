package com.example.slackbot.corona;

import com.example.slackbot.corona.dto.GetCoronaInfStateReq;
import com.example.slackbot.corona.dto.GetCoronaInfStateRes;
import com.example.slackbot.corona.dto.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CoronaClient {

    @Value("${corona.url.search}")
    private String coronaInfStateUrl;

    @Value("${corona.key.encoding}")
    private String coronaEncodingKey;

    @Value("${corona.key.decoding}")
    private String coronaDecodingKey;

    public Response getCoronaInfState(GetCoronaInfStateReq getCoronaInfStateReq){
        getCoronaInfStateReq.setServiceKey(coronaEncodingKey);

        var uri = UriComponentsBuilder.fromUriString(coronaInfStateUrl)
                .queryParams(getCoronaInfStateReq.toMultiValueMap())
                .build(true)
                .toUri();

        var headers = new HttpHeaders();
        var httpEntity = new HttpEntity<>(headers);
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        return parser(responseEntity.getBody());
    }

    public Response parser(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        Response response = null;

        try {
            response = xmlMapper.readValue(xml, Response.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }
}
