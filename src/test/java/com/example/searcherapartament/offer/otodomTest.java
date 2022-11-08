package com.example.searcherapartament.offer;

import com.example.searcherapartament.offer.scraping.OfferSystem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class otodomTest {
    @Autowired
    OfferSystem offerSystem;
    @Autowired
    com.example.searcherapartament.offer.scraping.otodom otodom;

        @Test
        public void shouldReturn400AdditionalCost()
        {
            String url = "https://www.otodom.pl/pl/oferta/dla-studenta-od-zaraz-nowe-budownictwo-blisko-wr-ID4j6W0";
            assertEquals(400,otodom.getAdditionalCost(url));
        }
        @Test
        public void shouldReturnLocation()
        {
            String url = "https://www.otodom.pl/pl/oferta/pokoj-bystrzycka-bajana-bez-dodatkowych-oplat-ID3gqoK";
            assertEquals("Gądów Mały, Wrocław, Wrocław",otodom.getLocation(url));
        }
}