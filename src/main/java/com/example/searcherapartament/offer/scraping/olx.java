package com.example.searcherapartament.offer.scraping;

import com.example.searcherapartament.offer.scraping.OfferSystem;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class olx {
    private final OfferSystem offerSystem;
    private final static int DIFFERENCETOREMOVE = 13;
    //cityNormalizedName\":\"wroclaw\",\"regionName\":\"Dolnośląskie\",
    // \"regionId\":3,\"regionNormalizedName\":\"dolnoslaskie\",\"districtName\"
    // :\"Psie Pole\",\"districtId\":389,\"pathName\":\"Dolnośląskie, Wrocław, Psie Pole\"},

    public int getAdditionalCost(String url)
    {
        Document document =offerSystem.getText(url);
        Elements rentAdditionally = document.select("li.css-ox1ptj");
        List<String> rents = rentAdditionally.eachText();

        //System.out.println(document.html());

        for(String rent:rents)
        {
            if(rent.contains("Czynsz (dodatkowo):"))
            {
                return   Integer.valueOf(rent.replace("Czynsz (dodatkowo):","").replace("zł", "").trim());
                // String nowyczynsz = rent.replace("zł", "").trim();
               // System.out.println("tutaj");
            }
        }
        return 0;
    }
   public String getLocation(String url)
    {
        String document =offerSystem.getText(url).toString();
        int index = document.indexOf("pathName");
        StringBuilder newDocument = new StringBuilder(document).delete(0,index+DIFFERENCETOREMOVE);
        String splitDocument = newDocument.toString().split("\"")[0];
        return splitDocument.substring(0,splitDocument.length()-1);
    }
}
