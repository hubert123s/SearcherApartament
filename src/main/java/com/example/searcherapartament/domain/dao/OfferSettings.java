package com.example.searcherapartament.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OfferSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private City city;
    private int numberOfPagesToScraping;
    private String perfectLocation;
    private Double latitudePerfectLocation;
    private Double longitudePerfectLocation;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Offer> offerList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TransportType transportType;//needed for TravelTime API

    private Long priceFrom;
    private Long priceTo;




}
