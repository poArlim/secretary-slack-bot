# secretary-slack-bot

매일 찾아보기 귀찮았던 정보들을 지정한 시간에 slack 메신저로 알려주는 **자동 알림 slack bot**

## 기능
1. 날씨 : 오늘 하루 날씨를 지정한 시간에 알림
<img width="609" alt="스크린샷 2021-08-25 오전 1 54 58" src="https://user-images.githubusercontent.com/43959582/130658086-1921c0e0-aa71-457c-b8b7-3f28766273e5.png">

2. 코로나 확진자 현황 : 어제 09:00 부터 오늘 09:00 까지의 코로나 확진자 수를 알림
<img width="535" alt="스크린샷 2021-08-25 오전 1 56 32" src="https://user-images.githubusercontent.com/43959582/130658272-7ccd8778-abd7-4f36-bd06-dc54353ed844.png">


## 사용방법
1. slack api 발급 및 postMessage 권한이 있는 app 추가 -> 구글링
2. [공공데이터포털](https://www.data.go.kr/) 회원가입 및 로그인
3. [기상청_단기예보 조회서비스](https://www.data.go.kr/data/15084084/openapi.do) 에서 API 활용신청 (API 신청 후 발급까지 1~2시간 소요됨)
4. [코로나19 감염현황](https://www.data.go.kr/data/15043376/openapi.do) 에서 API 활용신청 (API 신청 후 발급까지 1~2시간 소요됨)
5. `application.yaml` 에 개인 key 값들을 넣어서 추가
```yaml
slack :
  url :
    postMessage : https://slack.com/api/chat.postMessage
  client :
    token : 
    channel : 

weather :
  url :
    search :
      shortTerm : http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst
  key :
    encoding : 
    decoding : 

corona :
  url :
    search : http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson
  key :
    encoding : 
    decoding : 
```

6. `ApiController.java` 에서 원하는 알림 시간을 지정
  - `@Scheduled(cron = "초 분 시 일 월 요일 년(생략가능)")`
  - 일, 월, 요일을 `*` 로 표시하여 매일 알림을 받도록 설정할 수 있다.
  - 날씨API 는 날씨 예보 발표 시각이 **am 05:00** 이고, **am 06:00** 부터 받아올 수 있다. 그 이후 시간으로 설정하여야 한다.
  - 코로나API 는 확진자 수 발표 시각이 **am 09:00 ~ am 11:00** 이다. 그 이후 시간으로 설정하여야 안정적으로 값을 받아온다.



### 개인 노트 
1. 코로나19 감염현황 API 에서 response.body.items.item 의 resutlNegCnt(음성 결과 수) 오타 주의 (api 오류인듯)
2. secretary 가 입에 착 감기는 느낌은 아니다. 적절한 이름을 생각해보자.
3. 생각 날 때마다 기능 이것저것 붙여보자.
4. 관리페이지도 만들면 편할듯?



