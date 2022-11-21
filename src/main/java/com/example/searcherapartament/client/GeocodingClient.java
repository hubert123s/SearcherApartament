package com.example.searcherapartament.client;

import com.example.searcherapartament.domain.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GeocodingClient {
    @Qualifier("restTemplateConfiguration")
    private final RestTemplate restTemplate;
    @Value("${api_key}")
    private String API_KEY;
    @Value("${app_id}")
    private String APP_ID;

    private static final String URL ="https://api.traveltimeapp.com/v4/geocoding/search";

    //https://api.traveltimeapp.com/v4/geocoding/search?app_id=a146409c&api_key=57e038b5a52181b30649a4723fa702d4&query=Wrocław psie pole
   public ResultDto getLatitudeAndLongitude(String query)
    {
        if(query.contains("ul."))// API can't geocoding street
        {
            return  restTemplate.getForObject(URL+"?app_id={APP_ID}&api_key={API_KEY}&query={query}", ResultDto.class,APP_ID,API_KEY
                    ,query.split("ul.")[0]);

        }
        return  restTemplate.getForObject(URL+"?app_id={APP_ID}&api_key={API_KEY}&query={query}", ResultDto.class,APP_ID,API_KEY,query);

    }

}
