package com.example.searcherapartament.offer;

import com.example.searcherapartament.offer.model.OfferSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferSettings,Long> {
}
