package com.example.searcherapartament.offer;

import com.example.searcherapartament.offer.model.OfferSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/offer")
public class OfferControler {

    private final OfferService offerService;

    @PostMapping
    ResponseEntity<OfferSettings> saveSettings(@RequestBody OfferSettings offerSettings)
    {
        OfferSettings savedSettings = offerService.saveOfferSetings(offerSettings);
        URI savedMealUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSettings.getId())
                .toUri();
        return ResponseEntity.created(savedMealUri).body(savedSettings);
    }
}
