package com.example.searcherapartament.offer;

import com.example.searcherapartament.offer.scraping.OfferSystem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class olxTest {
    @Autowired
    OfferSystem offerSystem;
    @Autowired
    com.example.searcherapartament.offer.scraping.olx olx;

    @Test
    void shouldReturn600AdditionalCost()
    {
        String url ="https://www.olx.pl/d/oferta/2-pokoje-osobna-kuchnia-duzy-balkon-CID3-IDRHQTT.html";
        System.out.println(olx.getAdditionalCost(url));
        assertEquals(600,olx.getAdditionalCost(url));
    }

    @Test
    void shouldReturnLocation()
    {
        String url="https://www.olx.pl/d/oferta/2-pokoje-osobna-kuchnia-duzy-balkon-CID3-IDRHQTT.html";
        assertEquals("Dolnośląskie, Wrocław, Psie Pole",olx.getLocation(url));
    }

}