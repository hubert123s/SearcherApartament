package com.example.searcherapartament.service;

import com.example.searcherapartament.client.GeocodingClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeocodingService {
    private final GeocodingClient geocodingClient;
    public Double getLatitude(String query)
    {
        return geocodingClient.getLatitudeAndLongitude(query).getFeatures().get(0).getGeometry().getCoordinates().get(1);
    }
    public Double getLongitude(String query)
    {
        return geocodingClient.getLatitudeAndLongitude(query).getFeatures().get(0).getGeometry().getCoordinates().get(0);
    }
    public String getLocationsForTravelTimeAPI(String query)
    {
        return getLatitude(query)+"_"+getLongitude(query);
    }
}
