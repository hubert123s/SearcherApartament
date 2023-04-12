package com.example.searcherapartament.domain.dao;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String locationName;
    private Long allCosts;
    private String link;//Offer
    private Long time;
    private String imageLink;
}
