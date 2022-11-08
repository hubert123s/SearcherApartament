package com.example.searcherapartament.offer.scraping;

import com.example.searcherapartament.offer.scraping.OfferSystem;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component

public class otodom {
    private final OfferSystem offerSystem;
    private final static int DIFFERENCETOREMOVE = 28;

   public int getAdditionalCost(String url)
    {
        String document = offerSystem.getText(url).toString();
        int index = document.lastIndexOf("additional_cost");
        StringBuilder newDocument = new StringBuilder(document).delete(0,index+DIFFERENCETOREMOVE);
        return  Integer.valueOf(newDocument.toString().split("zł")[0].trim());
    }
   public String getLocation(String url)
    {
        String divClass= "div.css-1k12nzr.eu6swcv15";
        Document document = offerSystem.getText(url);
        return document.select(divClass).text();
    }

}
