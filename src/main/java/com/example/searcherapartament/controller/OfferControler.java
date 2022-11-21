package com.example.searcherapartament.controller;

import com.example.searcherapartament.service.OfferService;
import com.example.searcherapartament.domain.dao.OfferSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/offer")
public class OfferControler {

    private final OfferService offerService;

    @PostMapping
    OfferSettings searchOffer(@RequestBody OfferSettings offerSettings)
    {
        return offerService.searchOffer(offerSettings);
    }
}
