package com.example.searcherapartament.geocoding;

import com.example.searcherapartament.geocoding.dto.ApiDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class CoordinateClient {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY="e3add84fccc2206d5c57ca7d9b655173";
    private static final String URL ="http://api.positionstack.com/v1/forward";
    private static final String COUNTRY = "PL";//PL - POLAND

    public ApiDto getLatitudeAndLongitude(String query)
    {
        //return  restTemplate.getForObject(URL+"?access_key={API_KEY}&q={query}&country={country}",ApiDto.class,API_KEY,query,COUNTRY);
        return  restTemplate.getForObject("http://api.positionstack.com/v1/forward?access_key=e3add84fccc2206d5c57ca7d9b655173&query=Wrocław",ApiDto.class);
    }
}
