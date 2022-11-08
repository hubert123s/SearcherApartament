package com.example.searcherapartament.geocoding;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CoordinateService {
    private final CoordinateClient coordinateClient;

    Double getLatitude(String query)
    {
         return coordinateClient.getLatitudeAndLongitude(query).getData().get(0).getLatitude();
    }
    Double getLongitude(String query)
    {
        return coordinateClient.getLatitudeAndLongitude(query).getData().get(0).getLongitude();
    }
    String getLocationsForTravelTimeAPI(String query)
    {
        String lat = coordinateClient.getLatitudeAndLongitude(query).getData().get(0).getLatitude().toString();
        String lon = coordinateClient.getLatitudeAndLongitude(query).getData().get(0).getLongitude().toString();
        return lat+"_"+lon;
    }


}
