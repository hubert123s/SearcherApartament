package com.example.searcherapartament.service;

import com.example.searcherapartament.client.TimeClient;
import com.example.searcherapartament.domain.dao.TransportType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class TimeService {
    private final TimeClient timeClient;

   public Long getTime(TransportType type, String locations, Double search_lat, Double search_lng)
    {
        try {
            return timeClient.getTime(type,locations,search_lat,search_lng)
                    .getResults().get(0)
                    .getLocations().get(0)
                    .getProperties().get(0)
                    .getTravel_time();
        }catch (IndexOutOfBoundsException exception)
        {
            exception.getMessage();
        }
        return 0L;
    }

    //dla walking np {
    //    "results": [
    //        {
    //            "search_id": "Matrix",
    //            "locations": [],
    //            "unreachable": [
    //                "51.1079,17.0385"
    //            ]
    //        }
    //    ]

}
