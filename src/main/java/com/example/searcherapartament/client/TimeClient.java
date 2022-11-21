package com.example.searcherapartament.client;

import com.example.searcherapartament.domain.dao.TransportType;
import com.example.searcherapartament.domain.dto.TimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TimeClient {
    @Qualifier("restTemplateConfiguration")
    private final RestTemplate restTemplate;
    @Value("${api_key}")
    private String API_KEY;
    @Value("${app_id}")
    private String APP_ID;
    private static final String URL ="https://api.traveltimeapp.com/v4/time-filter";
    private static final String arrival_time="2022-11-04T10:00:00Z"; // API can't calculate the time of the night
    public TimeDto getTime(TransportType transportType, String locations, Double search_lat, Double search_lng)
    {
        String type = transportType.toString();
        return  restTemplate.getForObject(URL+"?type={type}&arrival_time={arrival_time}&search_lat={search_lat}&search_lng={search_lng}&locations={locations}&app_id={APP_ID}&api_key={API_KEY}"
                ,TimeDto.class,type,arrival_time,search_lat,search_lng,locations,APP_ID,API_KEY);
    }
}
