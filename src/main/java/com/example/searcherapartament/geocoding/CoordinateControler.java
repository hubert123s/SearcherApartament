package com.example.searcherapartament.geocoding;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coordinate")
public class CoordinateControler {
    private final CoordinateService coordinateService;

    @GetMapping
    String getCoordinate(@RequestParam String query)
    {
       return coordinateService.getLatitude(query).toString()+coordinateService.getLongitude(query);
    }
}
