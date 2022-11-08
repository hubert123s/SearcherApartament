package com.example.searcherapartament.offer;

import com.example.searcherapartament.offer.model.OfferSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferRepository offerRepository;

    OfferSettings saveOfferSetings(OfferSettings offerSettings)
    {
        return offerRepository.save(offerSettings);
    }
}
