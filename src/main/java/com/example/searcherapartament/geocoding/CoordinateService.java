package com.example.searcherapartament.geocoding;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


}
