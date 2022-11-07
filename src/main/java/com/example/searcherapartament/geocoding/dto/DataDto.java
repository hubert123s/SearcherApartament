package com.example.searcherapartament.geocoding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataDto {
    private Double latitude;
    private Double longitude;
    private String label;
    private String name;
    private String street;
//    "latitude": 38.897675,
//                        "longitude": -77.036547,
//                        "label": "1600 Pennsylvania Avenue NW, Washington, DC, USA",
//                        "name": "1600 Pennsylvania Avenue NW",
//                        "type": "address",
//                        "number": "1600",
//                        "street": "Pennsylvania Avenue NW",
//                        "postal_code": "20500",
//                        "confidence": 1,
//                        "region": "District of Columbia",
//                        "region_code": "DC",
//                        "administrative_area": null,
//                        "neighbourhood": "White House Grounds",
//                        "country": "United States",
//                        "country_code": "US",
//                        "map_url": "http://map.positionstack.com/38.897675,-77.036547"
}
