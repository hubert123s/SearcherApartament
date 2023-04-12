package com.example.searcherapartament.controller;

import com.example.searcherapartament.service.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/geocoding")
public class GeocodingController {
    private final GeocodingService geocodingService;

    @GetMapping
    public String get(@RequestParam String query)
    {
        return geocodingService.getLocationsForTravelTimeAPI(query);
    }
}
