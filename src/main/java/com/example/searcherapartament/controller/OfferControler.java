package com.example.searcherapartament.controller;

import com.example.searcherapartament.domain.dao.Offer;
import com.example.searcherapartament.service.OfferService;
import com.example.searcherapartament.domain.dao.OfferSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/offer")
public class OfferControler {

    private final OfferService offerService;

    @PostMapping
    OfferSettings searchOffer(@RequestBody OfferSettings offerSettings){
        return offerService.searchOffer(offerSettings);
    }
    @GetMapping("/{id}")
    List<Offer> selectedOffer(@PathVariable Long id){
        return offerService.selectedOffer(id);
    }
}
