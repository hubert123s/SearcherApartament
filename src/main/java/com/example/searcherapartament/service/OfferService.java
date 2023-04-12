package com.example.searcherapartament.service;

import com.example.searcherapartament.domain.dao.Offer;
import com.example.searcherapartament.domain.dao.OfferSettings;
import com.example.searcherapartament.scraper.OfferSystem;
import com.example.searcherapartament.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferSystem offerSystem;
    private final GeocodingService geocodingService;

    public List<Offer> selectedOffer(Long id) {
        return offerRepository.findById(id)
                .stream()
                .flatMap(offerSettings -> offerSettings.getOfferList().stream())
                .toList();
    }

    public OfferSettings searchOffer(OfferSettings offerSettings){
        String query = offerSettings.getPerfectLocation();
        offerSettings.setLatitudePerfectLocation(geocodingService.getLatitude(query));

        offerSettings.setLongitudePerfectLocation(geocodingService.getLongitude(query));
        List<Offer> selectedOffer = offerSystem.getOffer(offerSettings)
                .stream().filter(offer -> offer.getAllCosts()>offerSettings.getPriceFrom()&&offer.getAllCosts()<offerSettings.getPriceTo())
                .toList();
        offerSettings.setOfferList(selectedOffer);
        offerRepository.save(offerSettings);
        return offerSettings ;
    }
    public List<OfferSettings> findAllOfferSettings(){
        return offerRepository.findAll();
    }


}
