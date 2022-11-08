package com.example.searcherapartament.offer.model;

import com.example.searcherapartament.offer.model.OfferType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OfferSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String email;
    private Long priceFrom;
    private Long priceTo;
    @Enumerated(EnumType.STRING)
    private OfferType offerType;
    private String perfectLocation;
    private Double latitudePerfectLocation;
    private Double longitudePerfectLocation;
}
