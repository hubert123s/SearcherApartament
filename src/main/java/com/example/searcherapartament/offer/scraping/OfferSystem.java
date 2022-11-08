package com.example.searcherapartament.offer.scraping;

import org.springframework.stereotype.Component;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class OfferSystem {

    List<String> getAllLinks(Document document)
    {
        List<String> otodomLink =new ArrayList<>();
        List<String> olxLink =new ArrayList<>();
        //https://www.olx.pl/d/nieruchomosci/mieszkania/wynajem/wroclaw/
        Elements selections = document.select("a[href]");
        for (Element element : selections) {
//            System.out.println("link tutaj :"+element.attr("href"));
//            System.out.println("link tutaj ze split :"+element.attr("href").split(".html")[0]);
//            //  System.out.println("element.text"+element.text());
            if(element.attr("href").split(".html")[0].contains("pl/oferta"))
            {
                otodomLink.add(element.attr("href").split(".html")[0]);
            }
            else if(element.attr("href").split(".html")[0].contains("d/oferta"))
            {
                olxLink.add(element.attr("href").split(".html")[0]);
            }

        }
        return otodomLink;
    }
    Document getText(String url)
    {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
