package com.example.slackbot.corona.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetCoronaInfStateRes {

    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    public static class Body {
        private List<Item> items;
        private int pageNo;
        private int numOfRows;
        private int totalCount;
    }

    @Data
    public static class Item {
        private String stateDt;     // 기준일
        private String stateTime;   // 기준시간
        private int decideCnt;      // 확진자 수
        private int clearCnt;       // 격리해제 수
        private int examCnt;        // 검사진행 수
        private int deathCnt;       // 사망자 수
        private int careCnt;        // 치료중 환자 수
        private int resutlNegCnt;   // 결과 음성 수
        private int accExamCnt;     // 누적 검사 수
        private int accExamCompCnt; // 누적 검사 완료 수
        private double accDefRate;  // 누적 확진률
    }
}