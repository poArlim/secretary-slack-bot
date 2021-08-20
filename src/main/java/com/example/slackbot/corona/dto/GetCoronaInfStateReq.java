package com.example.slackbot.corona.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetCoronaInfStateReq {
    private String serviceKey;
    private int numOfRows;
    private int pageNo;
    private String startCreateDt;
    private String endCreateDt;


    public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("serviceKey", serviceKey);
        map.add("numOfRows", String.valueOf(numOfRows));
        map.add("pageNo", String.valueOf(pageNo));
        map.add("startCreateDt", startCreateDt);
        map.add("endCreateDt", endCreateDt);

        return map;
    }
}
