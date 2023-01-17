package com.example.searcherapartament.offer;

import com.example.searcherapartament.scraper.Olx;
import com.example.searcherapartament.scraper.OfferSystem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class olxTest {
    @Autowired
    OfferSystem offerSystem;
    @Autowired
    Olx olx;


    @Test
    void shouldReturnBasicPrice()
    {
        String url ="https://www.olx.pl/d/oferta/pokoj-jednoosobowy-do-wynajecia-w-cent-wroclawia-przy-ul-krynickiej-CID3-IDNHmDa.html";
        assertEquals(700,olx.getBasicCost(url));
    }
    @Test
    @DisplayName("0,01 zł")
    void shouldReturnAdditionalCostswithSpecialCharacter()
    {
        String url ="https://www.olx.pl/d/oferta/2-pok-52-m2-3-5-km-od-rynku-wroclaw-karlowice-prestizowa-lokalizacja-CID3-IDR9nMP.html";
        assertEquals(0,olx.getAdditionalCost(url));
    }

    @Test
    void shouldReturnBasicPricewithWhiteSpace()
    {
        String url ="https://www.olx.pl/d/oferta/pokoj-dla-studenta-1200-zl-osiedle-ksiaze-wielkie-CID3-IDRB3PA.html";
        assertEquals(1200,olx.getBasicCost(url));
    }
    @Test
    @DisplayName("Cost with white space")
    void shouldReturn1000AdditionalCost()
    {
        String url ="https://www.olx.pl/d/oferta/mieszkanie-3-pokojowe-56m-we-wroclawiu-rozkladowe-przy-ul-legnickiej-CID3-IDPcmZQ.html";
        System.out.println(olx.getAdditionalCost(url));
        assertEquals(1000,olx.getAdditionalCost(url));
    }

    @Test
    void shouldReturnLocation()
    {
        String url="https://www.olx.pl/d/oferta/2-pokoje-osobna-kuchnia-duzy-balkon-CID3-IDRHQTT.html";
        assertEquals("Dolnośląskie, Wrocław, Psie Pole",olx.getLocation(url));
    }

}